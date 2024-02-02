package com.example.exercise_21.presentation.screen.clothes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise_21.data.remote.common.Resource
import com.example.exercise_21.domain.model.GetClothes
import com.example.exercise_21.domain.usecase.clothes.GetClothesUseCase
import com.example.exercise_21.domain.usecase.local.clothes.GetLocalClothesUseCase
import com.example.exercise_21.domain.usecase.local.clothes.InsertClothesUseCase
import com.example.exercise_21.observeconnectivity.ConnectivityObserver
import com.example.exercise_21.observeconnectivity.NetworkConnectivityObserver
import com.example.exercise_21.presentation.event.clothes.ClothesEvents
import com.example.exercise_21.presentation.mapper.clothes.toPresenter
import com.example.exercise_21.presentation.state.clothes.ClothesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClothesViewModel @Inject constructor(
    private val clothesUseCase: GetClothesUseCase,
    private val insertClothesUseCase: InsertClothesUseCase,
    private val getLocalClothesUseCase: GetLocalClothesUseCase
) : ViewModel() {

    private lateinit var connectivityObserver: ConnectivityObserver

    private val _clothesState = MutableStateFlow(ClothesState())
    val clothesState: SharedFlow<ClothesState> = _clothesState.asStateFlow()


    fun onEvent(event: ClothesEvents) {
        when (event) {
            is ClothesEvents.FetchClothes -> fetchClothes()
        }
    }

    private fun fetchClothes() {
        viewModelScope.launch {
            clothesUseCase().collect {
                when (it) {
                    is Resource.Loading -> _clothesState.update { currentState ->
                        currentState.copy(
                            isLoading = it.isLoading
                        )
                    }

                    is Resource.Failure -> updateErrorMessage(message = it.errorMessage)

                    is Resource.Success -> handleSuccessCase(it.result)
                }
            }
        }

    }

    private fun handleSuccessCase(result: List<GetClothes>) {
        _clothesState.update { currentState ->
            currentState.copy(
                clothes = result.map {
                    it.toPresenter()
                }

            )
        }
        viewModelScope.launch {
            insertClothesUseCase(result)

        }
        viewModelScope.launch {
            getLocalClothesUseCase().collect{
                println("this is clothes from local datastore -> $it")
            }
        }



    }

    private fun updateErrorMessage(message: String) {
        _clothesState.update { currentState -> currentState.copy(errorMessage = message) }
    }
}

