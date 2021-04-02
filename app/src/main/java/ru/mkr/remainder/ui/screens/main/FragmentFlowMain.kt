package ru.mkr.remainder.ui.screens.main

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.mkr.remainder.R
import ru.mkr.remainder.databinding.FragmentFlowMainBinding
import ru.mkr.remainder.ui.base.BaseScreen
import ru.mkr.remainder.ui.navigation.RouterMainScreen
import javax.inject.Inject

@AndroidEntryPoint
class FragmentFlowMain : BaseScreen<FragmentFlowMainBinding>() {

    @Inject
    lateinit var router: RouterMainScreen

    override fun onResume() {
        super.onResume()
        router.injectController((childFragmentManager.findFragmentById(R.id.nav_container_main) as NavHostFragment).navController)
        _binding?.bottomNavView?.setupWithNavController(router.controller)
    }
}