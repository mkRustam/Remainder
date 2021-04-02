package ru.mkr.remainder.ui.screens.tasks.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.entity.Status
import ru.mkr.remainder.databinding.FragmentTasksBinding
import ru.mkr.remainder.ui.base.BaseScreen
import ru.mkr.remainder.ui.entity.tasks.TaskUiEntity
import ru.mkr.remainder.ui.screens.tasks.detail.FragmentTaskDetail
import javax.inject.Inject

@AndroidEntryPoint
class FragmentTasks : BaseScreen<FragmentTasksBinding>() {
    // https://github.com/amaljofy/NewsList-Part-1/blob/master/app/src/main/java/digital/lamp/dagger_test/ui/list/NewsListingFragment.kt
    // https://github.com/sberoch/RickAndMorty-AndroidArchitectureSample/blob/master/app/src/main/java/com/example/rickandmorty/data/repository/CharacterRepository.kt
    /*
    https://crudcrud.com/Dashboard/b85e2ae9698142e3af524a0f5f989567
     */
    // https://androidwave.com/android-data-binding-recyclerview/
    // https://developer.android.com/topic/libraries/architecture/coroutines
    // https://github.com/alexmamo/FirebaseApp-Clean-Architecture-MVVM/blob/master/app/src/main/java/ro/alexmamo/firebaseapp/auth/AuthRepository.java

    /**
     * READ
     * https://github.com/bmoeskau/Extensible/blob/master/recurrence-overview.md
     * https://icalendar.org/iCalendar-RFC-5545/3-8-5-3-recurrence-rule.html
     * */

    /** CURRENT TASK:
     *
     * https://github.com/ydhnwb/android-clean-architecture
     * https://www.raywenderlich.com/3595916-clean-architecture-tutorial-for-android-getting-started#toc-anchor-011
     * https://nuancesprog.ru/p/11445/
     * https://github.com/android/architecture-components-samples/blob/main/GithubBrowserSample
      */

    /**
     * From video https://www.youtube.com/watch?v=E82gJcFZ8YA
     * 1) [24:00-25:50] Use Lazy<Class> for variables if they not necessary at initialization moment. Especially when you use Dagger Injecting.
     * */

    /**
     * TODO
     * 0) Реализация логики запросов к бэку / кэширования через стратегии
     * 1) Add coroutineScope to data layer (Google Guide to Architecture)
     * 3) Main screen
     * 3.1) Calendar
     * 4) Widget
     * 5) Design
     * 6) Think how to save repeatable tasks in Db
     * Extra tasks:
     * - Swipe task for delete/update
     * - Пересмотреть https://www.youtube.com/watch?v=FdoiaitKTBs
     * - Обработка forceUpdate в репо
     * - Fix validation FieldInputText
     * - Handle FirebaseAuthRecentLoginRequiredException. https://firebase.google.com/docs/auth/android/manage-users#re-authenticate_a_user
     * - Add downloadable images for alert screen
     * - Move activity to viewBinding
     *
     * check later:
     * - https://github.com/PatilShreyas/Foodium/tree/master/app
     * - add https://github.com/kirich1409/ViewBindingPropertyDelegate
     * + https://github.com/PatilShreyas/Foodium/blob/master/app/src/main/java/dev/shreyaspatil/foodium/data/repository/NetworkBoundRepository.kt
     * */

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
        _binding!!.tasks.layoutManager = LinearLayoutManager(activity)
        _binding!!.tasks.setHasFixedSize(true)

        adapterTasks.setClickListener { task ->
            navigation.taskDetail(FragmentTaskDetail.getBundle(task.id!!))
        }

        _binding!!.tasks.adapter = adapterTasks
    }

    private fun initButton() {
        _binding!!.fabTaskAdd.setOnClickListener {
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