package com.projects.ui_components.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.projects.ui_components.databinding.ViewNavbarBinding

class NavbarView : FrameLayout {

    private var _binding: ViewNavbarBinding = ViewNavbarBinding.inflate(LayoutInflater.from(context), this, true)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setTitle(text: String): NavbarView {
        _binding.navbarTitle.text = text
        return this
    }

    fun setBackClickListener(listener: () -> Unit): NavbarView {
        _binding.navbarBack.setOnClickListener { listener.invoke() }
        return this
    }

}