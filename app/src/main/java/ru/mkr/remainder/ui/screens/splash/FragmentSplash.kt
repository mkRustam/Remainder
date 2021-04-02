package ru.mkr.remainder.ui.screens.splash

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.mkr.domain.entity.EntityUser
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.entity.Status
import ru.mkr.remainder.databinding.FragmentSplashBinding
import ru.mkr.remainder.ui.base.BaseScreen
import ru.mkr.remainder.utils.activity_result_contracts.ContractBatteryOptimizationIgnore
import javax.inject.Inject

@AndroidEntryPoint
class FragmentSplash : BaseScreen<FragmentSplashBinding>() {

    private val viewModel: ViewModelSplash by viewModels()
    @Inject
    lateinit var contractBatteryOptimizationIgnore: ContractBatteryOptimizationIgnore
    private lateinit var batteryOptimizationIgnore: ActivityResultLauncher<String>
    @Inject
    lateinit var navigation: NavigationSplash

    override fun onAttach(context: Context) {
        super.onAttach(context)
        batteryOptimizationIgnore = registerForActivityResult(contractBatteryOptimizationIgnore) { isInWhitelist ->
            if(!isInWhitelist) Toast.makeText(requireContext(), "Please add app to whitelisted", Toast.LENGTH_SHORT).show()
            navigation.main()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        // batteryOptimizationIgnore.launch(requireActivity().packageName)
        if(autologin.status == Status.SUCCESS) navigation.main()
        else navigation.auth()
    }
}