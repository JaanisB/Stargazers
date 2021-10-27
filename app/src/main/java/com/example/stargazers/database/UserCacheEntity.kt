package com.example.stargazers.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "users")
data class UserCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "login")
    var login: String,

    @ColumnInfo(name = "imgSrcUrl")
    var imgSrcUrl: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "siteAdmin")
    var siteAdmin: Boolean,

)

