package com.example.stargazers.network

import com.example.stargazers.model.User
import com.example.stargazers.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<UserNetworkEntity, User> {
    override fun mapFromEntity(entity: UserNetworkEntity): User {
        return User(
            id = entity.id,
            login = entity.login,
            imgSrcUrl = entity.imgSrcUrl,
            type = entity.type,
            siteAdmin = entity.siteAdmin
        )
    }

    override fun mapToEntity(domainModel: User): UserNetworkEntity {
        return UserNetworkEntity(
            id = domainModel.id,
            login = domainModel.login,
            imgSrcUrl = domainModel.imgSrcUrl,
            type = domainModel.type,
            siteAdmin = domainModel.siteAdmin
        )
    }

    fun mapFromEntityList(entities: List<UserNetworkEntity>) : List<User> {
        return entities.map {
            mapFromEntity(it)
        }
    }
}

