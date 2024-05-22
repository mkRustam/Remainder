package com.projects.remainder.ui.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    fun visible(view: View) {
        visible(view, true)
    }

    fun gone(view: View) {
        visible(view, false)
    }

    fun visible(view: View, show: Boolean) {
        view.visibility = if(show) View.VISIBLE else View.GONE
    }

    fun isVisible(view: View): Boolean {
        return view.visibility == View.VISIBLE
    }

}