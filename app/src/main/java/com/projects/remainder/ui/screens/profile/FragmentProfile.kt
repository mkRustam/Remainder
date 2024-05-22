package com.projects.remainder.ui.screens.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.projects.domain.entity.Resource
import com.projects.remainder.databinding.FragmentProfileBinding
import com.projects.remainder.ui.base.BaseScreen
import javax.inject.Inject

@AndroidEntryPoint
class FragmentProfile : BaseScreen<FragmentProfileBinding>() {

    private val viewModel: ViewModelProfile by viewModels()

    @Inject
    lateinit var navigation: NavigationProfile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        initViews()

        launch { viewModel.collectScreenState { changeViewState(it) } }
    }

    private fun initViews() {
        binding!!.buttonLogout.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun changeViewState(state: UiStateProfile) {
        if(state.logout() != null) updateLogoutState(state.logout()!!)
    }

    private fun updateLogoutState(logout: Resource<Boolean>) {
        navigation.logout()
    }
}