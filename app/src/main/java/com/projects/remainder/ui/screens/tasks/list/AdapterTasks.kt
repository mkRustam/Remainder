package com.projects.remainder.ui.screens.tasks.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projects.remainder.databinding.ItemTaskBinding
import com.projects.remainder.ui.entity.tasks.TaskUiEntity
import com.projects.remainder.ui.screens.tasks.list.AdapterTasks.TaskViewHolder
import java.util.*

class AdapterTasks : RecyclerView.Adapter<TaskViewHolder>() {
    private var tasks: List<TaskUiEntity> = ArrayList()
    private var listenerClick: ((TaskUiEntity) -> Unit)? = null

    //region ******************** OVERRIDE *********************************************************

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemTaskBinding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding, listenerClick)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    //endregion OVERRIDE

    //region ******************** PUBLIC ***********************************************************

    fun setTasks(tasks: List<TaskUiEntity>?) {
        if(tasks == null) this.tasks = ArrayList()
        else this.tasks = tasks
        notifyDataSetChanged()
    }

    fun setClickListener(listener: (TaskUiEntity) -> Unit) {
        this.listenerClick = listener
    }

    //endregion PUBLIC

    class TaskViewHolder(var viewBinding: ItemTaskBinding, listenerClick: ((TaskUiEntity) -> Unit)?) : RecyclerView.ViewHolder(viewBinding.root) {

        private lateinit var task: TaskUiEntity

        init {
            listenerClick?.let { listener ->
                viewBinding.root.setOnClickListener { listener.invoke(task) }
            }
        }

        fun bind(task: TaskUiEntity) {
            this.task = task
            viewBinding.title.text = task.title
            viewBinding.date.text = task.date
        }
    }
}