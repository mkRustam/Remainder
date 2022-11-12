package ru.mkr.remainder.ui.screens.tasks.base

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import ru.mkr.domain.entity.EntityTask
import ru.mkr.remainder.R
import ru.mkr.remainder.databinding.FragmentTaskEditBinding
import ru.mkr.remainder.ui.base.BaseScreen

abstract class FragmentTaskEditor: BaseScreen<FragmentTaskEditBinding>() {

    protected open val taskId: String? = null

    protected abstract fun submitForm(task: EntityTask)
    @StringRes protected abstract fun getButtonText(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    open fun init() {
        binding?.fieldTaskTitle?.setTitle(getString(R.string.field_title_name))
        binding?.fieldTaskDate?.setWithTime()?.setTitle(getString(R.string.field_title_date))

        binding!!.button.setText(getButtonText())
        binding!!.button.setOnClickListener {
            binding?.fieldTaskTitle?.validate(true) { titleSuccess ->
                if(titleSuccess) binding?.fieldTaskDate?.validate(true) { dateSuccess ->
                    if(dateSuccess) submitForm(createTask())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun createTask(): EntityTask {
        return EntityTask(taskId, binding?.fieldTaskTitle?.getValue()!!, binding?.fieldTaskDate?.getValue()!!)
    }
}