package com.fakhry.lifelog.favorites.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fakhry.lifelog.components.adapters.ListDateWithNoteAdapter
import com.fakhry.lifelog.favorites.databinding.FragmentFavoriteBinding
import com.fakhry.lifelog.favorites.di.initFavoriteKoinInjection
import com.fakhry.lifelog.utils.clickWithDebounce
import com.fakhry.lifelog.utils.coroutines.collectIn
import com.fakhry.lifelog.utils.goneIf
import com.fakhry.lifelog.utils.state.UiResult
import com.fakhry.lifelog.utils.state.isEmpty
import com.fakhry.lifelog.utils.visibleIf
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment(), AndroidScopeComponent {

    override val scope by fragmentScope()
    private val viewModel by viewModel<FavoriteViewModel>()
    private val dateWithNoteAdapter by lazy { ListDateWithNoteAdapter() }

    private lateinit var binding: FragmentFavoriteBinding

    init {
        initFavoriteKoinInjection()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteNote()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initLiveData()

        binding.btnGoToDashboard.clickWithDebounce {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    private fun initView() = with(binding) {
        rvFavorite.adapter = dateWithNoteAdapter
        rvFavorite.setHasFixedSize(true)
    }

    private fun initLiveData() = with(viewModel) {
        favoritesState.collectIn(this@FavoriteFragment) { state ->
            showEmptyState(state.isEmpty())
            if (state is UiResult.Success) dateWithNoteAdapter.submitList(state.data)
        }
    }

    private fun showEmptyState(isEmpty: Boolean) = with(binding) {
        ivEmptyDashboard.visibleIf(isEmpty)
        tvEmptyDashboard.visibleIf(isEmpty)
        btnGoToDashboard.visibleIf(isEmpty)
        rvFavorite.goneIf(isEmpty)
    }
}