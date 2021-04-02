package ru.mkr.data.database

import ru.mkr.data.Constants
import ru.mkr.data.database.entity.EntityDb
import java.util.*

abstract class LocalDataSourceBase {

    protected fun <T : EntityDb> makeExpire(data: T) {
        data._timestamp = Calendar.getInstance(TimeZone.getTimeZone(Constants.App.DB_TIME_ZONE)).timeInMillis
    }

    protected fun <T : EntityDb> makeExpireList(dataList: List<T>) {
        dataList.map { makeExpire(it) }
    }
}