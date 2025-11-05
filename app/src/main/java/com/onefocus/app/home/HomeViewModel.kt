package com.onefocus.app.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onefocus.app.R

class HomeViewModel : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    init {
        _tasks.value = listOf(
            Task("+ Custom Task", R.drawable.ic_add),
            Task("Exercise", R.drawable.ic_exercise),
            Task("Reading", R.drawable.ic_reading),
            Task("Meditation", R.drawable.ic_meditation),
            Task("Study", R.drawable.ic_study),
            Task("Work", R.drawable.ic_work),
            Task("Social", R.drawable.ic_social),
            Task("Music", R.drawable.ic_music),
            Task("Journaling", R.drawable.ic_journaling),
            Task("Cleaning", R.drawable.ic_cleaning),
            Task("Running", R.drawable.ic_running)
        )
    }
}
