package com.projects.remainder.ui.screens.main

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import com.projects.remainder.R
import com.projects.remainder.databinding.FragmentFlowMainBinding
import com.projects.remainder.ui.base.BaseScreen
import com.projects.remainder.ui.navigation.RouterMainScreen
import javax.inject.Inject

@AndroidEntryPoint
class FragmentFlowMain : BaseScreen<FragmentFlowMainBinding>() {

    @Inject
    lateinit var router: RouterMainScreen

    override fun onResume() {
        super.onResume()
        router.injectController((childFragmentManager.findFragmentById(R.id.nav_container_main) as NavHostFragment).navController)
        binding?.bottomNavView?.setupWithNavController(router.controller)
    }
}