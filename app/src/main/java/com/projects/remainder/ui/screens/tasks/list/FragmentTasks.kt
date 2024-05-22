package com.projects.remainder.ui.screens.tasks.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import com.projects.domain.entity.Resource
import com.projects.domain.entity.Status
import com.projects.remainder.databinding.FragmentTasksBinding
import com.projects.remainder.ui.base.BaseScreen
import com.projects.remainder.ui.entity.tasks.TaskUiEntity
import com.projects.remainder.ui.screens.tasks.detail.FragmentTaskDetail
import javax.inject.Inject

@AndroidEntryPoint
class FragmentTasks : BaseScreen<FragmentTasksBinding>() {

    private val adapterTasks: AdapterTasks by lazy { AdapterTasks() }
    private val viewModel: ViewModelTasks by viewModels()

    @Inject
    lateinit var navigation: NavigationTasks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) viewModel.loadTasks()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        initRecycler()
        initButton()

        launch { viewModel.collectScreenState { changeViewState(it) } }
    }

    private fun initRecycler() {
        // bind RecyclerView
        binding!!.tasks.layoutManager = LinearLayoutManager(activity)
        binding!!.tasks.setHasFixedSize(true)

        adapterTasks.setClickListener { task ->
            navigation.taskDetail(FragmentTaskDetail.getBundle(task.id!!))
        }

        binding!!.tasks.adapter = adapterTasks
    }

    private fun initButton() {
        binding!!.fabTaskAdd.setOnClickListener {
            navigation.taskAdd()
        }
    }

    private fun changeViewState(state: UiStateTasks) {
        if(state.tasks() != null) updateTasksState(state.tasks()!!)
    }

    private fun updateTasksState(tasks: Resource<List<TaskUiEntity>>) {
        if(tasks.status == Status.ERROR) {
            if(tasks.data != null) Log.d("App", "${this.javaClass.simpleName} [error] message")
            else {
                adapterTasks.setTasks(tasks.data)
                Log.d("App", "${this.javaClass.simpleName} [error] view")
            }
        }
        else {
            if(tasks.status == Status.LOADING) {
                Log.d("App", "${this.javaClass.simpleName} [loading]")
            }
            else if(tasks.status == Status.SUCCESS) {
                Log.d("App", "${this.javaClass.simpleName} [success] loading finished")
                if(tasks.data.isNullOrEmpty()) Log.d("App", "${this.javaClass.simpleName} empty view")
            }
            adapterTasks.setTasks(tasks.data)
        }
    }
}