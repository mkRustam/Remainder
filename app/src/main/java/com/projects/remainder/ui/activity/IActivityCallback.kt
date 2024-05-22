package com.projects.remainder.ui.activity

interface IActivityCallback {
    /**
     * @return true - event handled
     * false - pass event to parent
     * */
    fun onActivityBackPressed(): Boolean = false
}