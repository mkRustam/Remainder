package com.projects.remainder.ui.base

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.projects.remainder.R
import com.projects.remainder.ui.activity.IActivityCallback
import com.projects.ui_components.UiLocker
import com.projects.ui_components.common.NavbarView
import java.lang.reflect.ParameterizedType

abstract class BaseScreen<VB: ViewBinding>: Fragment(), IActivityCallback {

//    https://github.com/AlexGladkov/JetpackNavigationDemo
//    https://www.youtube.com/watch?v=fEGlZQcQZIA

    protected var binding: VB? = null
    private lateinit var screenLocker: UiLocker

    open fun getNavbar(): NavbarView? { return null }

    protected fun initNavBar(text: String) {
        getNavbar()?.setTitle(text)
        getNavbar()?.setBackClickListener { findNavController().popBackStack() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = createViewBindingInstance(
            clazz = getViewBindingFromGeneric(),
            inflater = inflater,
            container = container,
            attachToRoot = false
        )
        screenLocker = requireActivity().findViewById(R.id.screen_locker)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun createViewBindingInstance(
        clazz: Class<VB>,
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): VB {
        return clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        ).invoke(clazz, inflater, container, attachToRoot) as VB
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewBindingFromGeneric(): Class<VB> {
        return getBindingClass(javaClass)
    }

    private fun getBindingClass(clazz: Class<*>): Class<VB> {
        return if(clazz.genericSuperclass is ParameterizedType) (clazz.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VB>
        else getBindingClass(clazz.superclass)
    }

    protected inline fun Fragment.launch(
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
        crossinline block: suspend CoroutineScope.() -> Unit
    ) {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
                block()
            }
        }
    }

    protected fun toast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    protected fun toastNoEmpty(msg: String?, msg2: String) {
        toast(if(!TextUtils.isEmpty(msg)) msg!! else msg2)
    }

    protected fun errorUnavailable(): String {
        return getString(R.string.error_unavailable)
    }

    protected fun lockScreen() {
        screenLocker.lock()
    }

    protected fun unlockScreen() {
        screenLocker.unlock()
    }

    protected fun argString(key: String): String? {
        return requireArguments().getString(key)
    }
}