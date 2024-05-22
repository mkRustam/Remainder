package com.projects.data.mapper

interface IMapper<Db, Api, Domain> {
    fun fromApiToDb(data: Api): Db
    fun fromDbToDomain(data: Db): Domain
}