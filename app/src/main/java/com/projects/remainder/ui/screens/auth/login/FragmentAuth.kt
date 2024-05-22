package com.projects.remainder.ui.screens.auth.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.projects.domain.entity.EntityUser
import com.projects.domain.entity.Resource
import com.projects.domain.entity.Status
import com.projects.remainder.R
import com.projects.remainder.databinding.FragmentAuthBinding
import com.projects.remainder.ui.base.BaseScreen
import javax.inject.Inject

@AndroidEntryPoint
class FragmentAuth: BaseScreen<FragmentAuthBinding>() {

    private val viewModel: ViewModelAuth by viewModels()

    @Inject
    lateinit var navigation: NavigationAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        initViews()

        launch { viewModel.collectScreenState { changeViewState(it) } }
    }

    private fun initViews() {
        binding?.fieldEmail?.setTitle(getString(R.string.field_title_email))
        binding?.fieldPassword?.setTitle(getString(R.string.field_title_password))

        binding!!.buttonSignIn.setOnClickListener {
            binding?.fieldEmail?.validate(true) { emailValid ->
                if(emailValid) binding?.fieldPassword?.validate(true) { passwordValid ->
                    if(passwordValid) {
                        lockScreen()
                        viewModel.login(binding?.fieldEmail?.getText()!!, binding?.fieldPassword?.getText()!!)
                    }
                }
            }
        }
        binding!!.buttonRegister.setOnClickListener {
            navigation.register()
        }
    }

    private fun changeViewState(state: UiStateAuth) {
        if(state.login() != null) updateLoginState(state.login()!!)
    }

    private fun updateLoginState(autologin: Resource<EntityUser>) {
        unlockScreen()
        if(autologin.status == Status.ERROR) toastNoEmpty(autologin.message, errorUnavailable())
        else if(autologin.status == Status.SUCCESS && autologin.data != null) navigation.main()
    }
}