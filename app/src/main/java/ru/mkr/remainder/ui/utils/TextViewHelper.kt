package ru.mkr.remainder.ui.utils

import android.view.View
import android.widget.TextView

object TextViewHelper {

    fun setTextOrGone(textView: TextView, text: String?) {
        val show = text?.isNotEmpty() ?: false
        textView.text = text
        textView.visibility = if(show) View.VISIBLE else View.GONE
    }

}