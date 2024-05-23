package com.projects.ui_components.fields

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.projects.ui_components.R
import com.projects.ui_components.databinding.ViewFieldInputTextBinding

abstract class FieldInputBase<TValue>: FrameLayout {

    private val ERROR_EMPTY = R.string.field_error_required

    protected var _binding: ViewFieldInputTextBinding = ViewFieldInputTextBinding.inflate(LayoutInflater.from(context), this, true)

    private var optional = false
    private var validateByInput = false
    private var validationResult: ((Boolean) -> Unit)? = null
    private var customValidator: ((String?, TValue?) -> ValidatorResult)? = null
    private lateinit var textChangeListener: TextWatcher
    private lateinit var focusChangeListener: OnFocusChangeListener

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    protected abstract fun initField()
    protected abstract fun toValue(text: String?): TValue?

    fun setOptional() {
        this.optional = true
    }

    fun setValue(text: String?, value: TValue? = null, validate: Boolean = true) {
        _binding.fieldInputTextValue.setText(text)
        setValue(value ?: toValue(text), validate)
    }

    fun setValue(value: TValue?, validate: Boolean) {
        _binding.fieldInputText.tag = value
        if(validate) validate(true, validationResult)
    }

    fun setValidator(customValidator: (String?, TValue?) -> ValidatorResult) {
        this.customValidator = customValidator
    }

    fun setReadOnly() {
        setNoFocusable()
    }

    /**
     * Отключение возможности ручного ввода в поле.
     * Отключает фокусировку
     */
    fun setNoFocusable() {
        _binding.fieldInputTextValue.isFocusable = false
        _binding.fieldInputTextValue.isCursorVisible = false
        _binding.fieldInputTextValue.isLongClickable = false
        _binding.fieldInputTextValue.isFocusableInTouchMode = false
        _binding.fieldInputTextValue.removeTextChangedListener(textChangeListener)
        _binding.fieldInputTextValue.onFocusChangeListener = null
        _binding.fieldInputTextValue.isClickable = false
    }

    fun setClickListener(listener: OnClickListener?) {
        _binding.fieldInputTextValue.setOnClickListener(listener)
    }

    fun validateByInput(validationResult: (Boolean) -> Unit) {
        this.validationResult = validationResult
        this.validateByInput = true
    }

    fun setTitle(title: String?): FieldInputBase<TValue> {
        _binding.fieldInputTextTitle.text = title
        visible(_binding.fieldInputTextTitle, !title.isNullOrEmpty())
        return this
    }

    fun getValue(): TValue? {
        return _binding.root.tag as TValue?
    }

    fun getText(): String? {
        return _binding.fieldInputTextValue.text?.toString()
    }

    fun validate(showError: Boolean, resultListener: ((Boolean) -> Unit)?) {
        when {
            // Если текстовое поле пустое, то валидируем по флагу optional
            TextUtils.isEmpty(getText()?.trim()) -> {
                validationResult(optional, showError, ERROR_EMPTY, resultListener)
            }
            // Если есть кастомный обработчик, то используем его
            customValidator != null -> {
                val customResult = customValidator!!.invoke(getText(), getValue())
                validationResult(customResult.success, showError, customResult.errorResId, resultListener)
            }
            else -> {
                resultListener?.invoke(true)
            }
        }
    }

    init {
        initListeners()
        initField()

        // handle init value
        _binding.fieldInputTextValue.post {
            setValue(toValue(_binding.fieldInputTextValue.text?.toString()), false)
        }
    }

    private fun initListeners() {
        textChangeListener = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                setValue(toValue(getText()), false)
                if(validateByInput) validate(true, validationResult)
                else errorHide()
            }
        }

        focusChangeListener = OnFocusChangeListener { _, hasFocus -> if(!hasFocus) validate(true, null) }

        _binding.fieldInputTextValue.addTextChangedListener(textChangeListener)
        _binding.fieldInputTextValue.onFocusChangeListener = focusChangeListener
    }

    private fun validationResult(success: Boolean, showError: Boolean, errorResId: Int?, listener: ((Boolean) -> Unit)?) {
        if(success) errorHide()
        else if(showError && errorResId != null) errorShow(errorResId)
        listener?.invoke(success)
    }

    private fun errorShow(errorResId: Int) {
        visible(_binding.fieldInputTextError)
        _binding.fieldInputTextError.setText(errorResId)
        // todo [Красное подчеркивание] Отобразить
    }

    private fun errorHide() {
        gone(_binding.fieldInputTextError)
        // todo [Красное подчеркивание] Сбросить
    }

    private fun gone(v: View) {
        v.visibility = View.GONE
    }

    private fun visible(v: View) {
        v.visibility = View.VISIBLE
    }

    private fun visible(v: View, show: Boolean) {
        if(show) visible(v)
        else gone(v)
    }

    data class ValidatorResult(var success: Boolean, var errorResId: Int?)
}