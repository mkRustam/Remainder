package com.projects.remainder.ui.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import com.projects.remainder.R
import com.projects.remainder.ui.base.BaseActivity
import com.projects.remainder.ui.base.IUiLocker
import com.projects.remainder.ui.navigation.Router
import com.projects.ui_components.UiLocker
import javax.inject.Inject

@AndroidEntryPoint
class ActivityMain : BaseActivity(), IUiLocker {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var screenLocker: UiLocker

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        screenLocker = findViewById(R.id.screen_locker)
        router.injectController(findController())
    }

    override fun onResume() {
        super.onResume()
        router.injectController(findController())
    }

    override fun onBackPressed() {
        val fragment = navHostFragment.childFragmentManager.fragments[0]
        val eventHandled = (fragment as IActivityCallback?)?.onActivityBackPressed() ?: false
        if(!eventHandled) super.onBackPressed()
    }

    override fun lock() {
        screenLocker.lock()
    }

    override fun unlock() {
        screenLocker.unlock()
    }

    private fun findController(): NavController {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_container_view) as NavHostFragment
        return navHostFragment.navController
    }
}