package ru.mkr.data.repository.auth.mapper

import com.google.firebase.auth.FirebaseUser
import ru.mkr.data.mapper.IMapper
import ru.mkr.domain.entity.EntityUser

class MapperFirebaseUser: IMapper<FirebaseUser, FirebaseUser, EntityUser> {

    override fun fromApiToDb(data: FirebaseUser): FirebaseUser {
        return data
    }

    override fun fromDbToDomain(data: FirebaseUser): EntityUser {
        return EntityUser(data.uid)
    }
}