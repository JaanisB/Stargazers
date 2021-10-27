package com.example.stargazers.util

interface EntityMapper<NetworkEntity, DomainModel> {

    // Map from Network entity to Domain model (model - User)
    fun mapFromEntity (entity: NetworkEntity) : DomainModel

    //Map from Domain model to Network entity
    fun mapToEntity (domainModel: DomainModel): NetworkEntity
}