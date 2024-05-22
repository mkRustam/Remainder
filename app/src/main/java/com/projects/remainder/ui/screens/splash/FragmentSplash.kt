package com.projects.remainder.ui.screens.splash

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.projects.domain.entity.EntityUser
import com.projects.domain.entity.Resource
import com.projects.domain.entity.Status
import com.projects.remainder.databinding.FragmentSplashScreenBinding
import com.projects.remainder.ui.base.BaseScreen
import com.projects.remainder.utils.activity_result_contracts.ContractBatteryOptimizationIgnore
import javax.inject.Inject

@AndroidEntryPoint
class FragmentSplash : BaseScreen<FragmentSplashScreenBinding>() {

    @Inject
    lateinit var navigation: NavigationSplash

    @Inject
    lateinit var contractBatteryOptimizationIgnore: ContractBatteryOptimizationIgnore

    private val viewModel: ViewModelSplash by viewModels()

    private lateinit var batteryOptimizationIgnore: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        batteryOptimizationIgnore = registerForActivityResult(contractBatteryOptimizationIgnore) { isInWhitelist ->
            if(!isInWhitelist) Toast.makeText(requireContext(), "Please add app to whitelisted", Toast.LENGTH_SHORT).show()
            navigation.main()
        }
        viewModel.autologin()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        launch { viewModel.collectScreenState { changeViewState(it) } }
    }

    private fun changeViewState(state: UiStateSplash) {
        if(state.autologin() != null) updateAutologinState(state.autologin()!!)
    }

    private fun updateAutologinState(autologin: Resource<EntityUser>) {
        batteryOptimizationIgnore.launch(requireActivity().packageName)
        if(autologin.status == Status.SUCCESS) navigation.main()
        else navigation.auth()
    }
}