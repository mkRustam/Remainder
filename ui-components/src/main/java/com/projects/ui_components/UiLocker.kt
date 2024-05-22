package com.projects.ui_components

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.view.View
import android.view.LayoutInflater
import android.view.MotionEvent
import com.projects.ui_components.databinding.ViewScreenLockerBinding

class UiLocker : FrameLayout {

    private var binding = ViewScreenLockerBinding.inflate(LayoutInflater.from(context), this)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private fun init() {
        visibility = GONE
        setOnTouchListener { v: View?, event: MotionEvent? -> true }
    }

    fun lock() {
        visibility = VISIBLE
    }

    fun unlock() {
        visibility = GONE
    }

    val isLocked: Boolean
        get() = visibility == VISIBLE

    init {
        init()
    }
}