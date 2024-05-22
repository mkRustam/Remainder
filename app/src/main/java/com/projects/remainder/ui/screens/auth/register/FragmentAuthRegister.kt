package com.projects.remainder.ui.screens.auth.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.projects.domain.entity.EntityUser
import com.projects.domain.entity.Resource
import com.projects.domain.entity.Status
import com.projects.remainder.R
import com.projects.remainder.databinding.FragmentAuthRegisterBinding
import com.projects.remainder.ui.base.BaseScreen
import javax.inject.Inject

@AndroidEntryPoint
class FragmentAuthRegister: BaseScreen<FragmentAuthRegisterBinding>() {

    private val viewModel: ViewModelAuthRegister by viewModels()

    @Inject
    lateinit var navigation: NavigationAuthRegister

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

        binding?.buttonRegister?.setOnClickListener {
            binding?.fieldEmail?.validate(true) { emailValid ->
                if(emailValid) binding?.fieldPassword?.validate(true) { passwordValid ->
                    if(passwordValid) {
                        lockScreen()
                        viewModel.register(binding?.fieldEmail?.getText()!!, binding?.fieldPassword?.getText()!!)
                    }
                }
            }
        }
    }

    private fun changeViewState(state: UiStateAuthRegister) {
        if(state.register() != null) updateRegisterState(state.register()!!)
    }

    private fun updateRegisterState(register: Resource<EntityUser>) {
        unlockScreen()
        if(register.status == Status.ERROR) toastNoEmpty(register.message, errorUnavailable())
        else if(register.status == Status.SUCCESS) navigation.main()
    }
}