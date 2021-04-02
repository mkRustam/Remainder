package ru.mkr.remainder.ui.screens.auth.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.mkr.domain.entity.EntityUser
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.entity.Status
import ru.mkr.remainder.R
import ru.mkr.remainder.databinding.FragmentAuthBinding
import ru.mkr.remainder.ui.base.BaseScreen
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
        _binding?.fieldEmail?.setTitle(getString(R.string.field_title_email))
        _binding?.fieldPassword?.setTitle(getString(R.string.field_title_password))

        _binding!!.buttonSignIn.setOnClickListener {
            _binding?.fieldEmail?.validate(true) { emailValid ->
                if(emailValid) _binding?.fieldPassword?.validate(true) { passwordValid ->
                    if(passwordValid) {
                        lockScreen()
                        viewModel.login(_binding?.fieldEmail?.getText()!!, _binding?.fieldPassword?.getText()!!)
                    }
                }
            }
        }
        _binding!!.buttonRegister.setOnClickListener {
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