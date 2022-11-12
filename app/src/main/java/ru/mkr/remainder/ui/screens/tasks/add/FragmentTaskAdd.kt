package ru.mkr.remainder.ui.screens.tasks.add

import android.util.Log
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.mkr.domain.entity.EntityTask
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.entity.Status
import ru.mkr.remainder.R
import ru.mkr.remainder.ui.screens.tasks.base.FragmentTaskEditor
import ru.mkr.remainder.ui.views.common.NavbarView
import javax.inject.Inject

@AndroidEntryPoint
class FragmentTaskAdd : FragmentTaskEditor() {

    private val viewModel: ViewModelTaskAdd by viewModels()

    @Inject
    lateinit var navigation: NavigationTaskAdd

    override fun init() {
        super.init()
        initNavBar(getString(R.string.screen_title_task_new))

        launch { viewModel.collectScreenState { changeViewState(it) } }
    }

    override fun getNavbar(): NavbarView? {
        return binding?.navbar
    }

    override fun getButtonText(): Int = R.string.button_add

    override fun submitForm(task: EntityTask) {
        lockScreen()
        viewModel.addTask(EntityTask(null, task.title, task.dateTime))
    }

    private fun changeViewState(state: UiStateTaskAdd) {
        if(state.taskAdd() != null) updateTaskAddState(state.taskAdd()!!)
    }

    private fun updateTaskAddState(taskAdd: Resource<EntityTask>) {
        unlockScreen()
        if(taskAdd.status == Status.ERROR) {
            Log.d("App", "${this.javaClass.simpleName} [error] message")
        }
        else {
            if(taskAdd.status == Status.LOADING) {
                Log.d("App", "${this.javaClass.simpleName} [loading]")
            }
            else if(taskAdd.status == Status.SUCCESS) {
                Log.d("App", "${this.javaClass.simpleName} [success] loading finished")
                navigation.finish()
            }
        }
    }
}