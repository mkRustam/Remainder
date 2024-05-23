package com.projects.ui_components.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.KeyEvent
import android.view.View
import androidx.annotation.LayoutRes
import com.projects.ui_components.R

abstract class BaseDialog(val context: Context) {

    private var dialog: Dialog

    protected fun getTheme() = R.style.BaseDialog
    @LayoutRes protected abstract fun getLayoutId(): Int
    protected abstract fun initDialog()

    init {
        dialog = create()
        initDialog()
    }

    fun show() {
        if(!dialog.isShowing) dialog.show()
    }

    fun hide(): Boolean {
        if(dialog.isShowing) {
            dialog.dismiss()
            return true
        }
        else return false
    }

    protected fun create(): Dialog {
        val dialog = Dialog(context, getTheme())
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(getLayoutId())
        dialog.setOnKeyListener { di: DialogInterface?, i: Int, event: KeyEvent? ->
            val isBackPress = i == KeyEvent.KEYCODE_BACK
            if(dialog.isShowing) dialog.dismiss()
            isBackPress
        }
        return dialog
    }

    protected fun <T : View> findView(id: Int): T {
        return dialog.findViewById(id)
    }
}