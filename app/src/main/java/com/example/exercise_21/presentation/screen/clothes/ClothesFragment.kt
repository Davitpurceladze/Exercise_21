package com.example.exercise_21.presentation.screen.clothes

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.exercise_21.databinding.FragmentClothesBinding
import com.example.exercise_21.presentation.base.BaseFragment
import com.example.exercise_21.presentation.event.clothes.ClothesEvents
import com.example.exercise_21.presentation.state.clothes.ClothesState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ClothesFragment : BaseFragment<FragmentClothesBinding>(FragmentClothesBinding::inflate) {

    private val viewModel: ClothesViewModel by viewModels()
    private val clothesRecyclerAdapter: ClothesRecyclerAdapter by lazy { ClothesRecyclerAdapter() }

    override fun bind() {
        fetchClothes()
    }

    override fun bindViewActionListeners() {
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.clothesState.collect {
                    handleClothesState(it)
                }
            }
        }

    }

    private fun handleClothesState(state: ClothesState) {
        state.clothes?.let {
            createRecycler()
            clothesRecyclerAdapter.submitList(it)
        }
        println(state)
    }

    private fun createRecycler() {
        with(binding) {
            clothesRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
            clothesRecycler.adapter = clothesRecyclerAdapter
        }
    }

    private fun fetchClothes() {
        viewModel.onEvent(ClothesEvents.FetchClothes)
    }
}