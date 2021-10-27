package com.example.stargazers.database

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.stargazers.model.User
import com.example.stargazers.util.EntityMapper
import javax.inject.Inject
import kotlin.math.log

class CacheMapper
@Inject constructor() : EntityMapper<UserCacheEntity, User> {

    override fun mapFromEntity(entity: UserCacheEntity): User {
        return User(
            id = entity.id,
            login = entity.login,
            imgSrcUrl = entity.imgSrcUrl,
            type = entity.type,
            siteAdmin = entity.siteAdmin
        )
    }

    override fun mapToEntity(domainModel: User): UserCacheEntity {
        return UserCacheEntity(
            id = domainModel.id,
            login = domainModel.login,
            imgSrcUrl = domainModel.imgSrcUrl,
            type = domainModel.type,
            siteAdmin = domainModel.siteAdmin
        )
    }

    // Map from UserCache Entity list to User list
    fun mapFromEntityList (entities: List<UserCacheEntity>) : List<User> {
        return entities.map {
            mapFromEntity(it)
        }
    }
}

