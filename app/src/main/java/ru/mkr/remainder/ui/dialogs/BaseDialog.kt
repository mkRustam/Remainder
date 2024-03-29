package ru.mkr.remainder.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.KeyEvent
import android.view.View
import androidx.annotation.LayoutRes
import ru.mkr.remainder.R

abstract class BaseDialog(val context: Context) {

    private var dialog: Dialog

    //region ******************** PROTECTED ********************************************************

    protected fun getTheme() = R.style.BaseDialog
    @LayoutRes protected abstract fun getLayoutId(): Int
    protected abstract fun initDialog()

    //endregion PROTECTED

    init {
        dialog = create()
        initDialog()
    }

    public fun show() {
        if(!dialog.isShowing) dialog.show()
    }

    public fun hide(): Boolean {
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

    //region ******************** HELPERS **********************************************************

    protected fun <T : View> findView(id: Int): T {
        return dialog.findViewById(id)
    }

    //endregion HELPERS
}