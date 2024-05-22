package com.projects.ui_components.fields

import android.content.Context
import android.util.AttributeSet

open class FieldInputText : FieldInputBase<String> {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun initField() {

    }

    override fun toValue(text: String?): String? {
        return text
    }
}