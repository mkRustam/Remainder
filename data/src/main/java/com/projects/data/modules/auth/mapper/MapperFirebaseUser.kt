package com.projects.data.modules.auth.mapper

import com.google.firebase.auth.FirebaseUser
import com.projects.data.mapper.IMapper
import com.projects.domain.entity.EntityUser

class MapperFirebaseUser: IMapper<FirebaseUser, FirebaseUser, EntityUser> {

    override fun fromApiToDb(data: FirebaseUser): FirebaseUser {
        return data
    }

    override fun fromDbToDomain(data: FirebaseUser): EntityUser {
        return EntityUser(data.uid)
    }
}