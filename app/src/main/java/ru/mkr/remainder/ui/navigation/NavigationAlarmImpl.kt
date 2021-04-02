package ru.mkr.remainder.ui.navigation

import ru.mkr.remainder.ui.screens.alarm.NavigationAlarm
import javax.inject.Inject

class NavigationAlarmImpl @Inject constructor(
    private val router: Router
) : NavigationAlarm