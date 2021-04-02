package ru.mkr.remainder.utils

import java.util.Stack

class NavigationBottomViewStack: Stack<Int>() {

    override fun push(item: Int): Int {
        // Avoid duplicate lasts screens
        return if(isNotEmpty() && lastElement() != item) super.push(item)
        else item
    }

    fun pop(exclude: Int): Int? {
        while(isNotEmpty() && lastElement() == exclude) super.pop()
        return if(isNotEmpty()) super.pop()
        else return null
    }
}