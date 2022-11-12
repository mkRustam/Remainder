package ru.mkr.remainder.ui.views.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import ru.mkr.remainder.databinding.ViewNavbarBinding

class NavbarView : FrameLayout {

    private var _binding: ViewNavbarBinding = ViewNavbarBinding.inflate(LayoutInflater.from(context), this, true)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    public fun setTitle(text: String): NavbarView {
        _binding.navbarTitle.text = text
        return this
    }

    public fun setBackClickListener(listener: () -> Unit): NavbarView {
        _binding.navbarBack.setOnClickListener { listener.invoke() }
        return this
    }

}