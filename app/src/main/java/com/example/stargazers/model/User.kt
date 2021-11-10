package com.example.stargazers.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val login: String,
    @Json(name = "avatar_url")
    val imgSrcUrl: String,
    val type: String,
    @Json(name = "site_admin")
    val siteAdmin: Boolean
    ) : Parcelable






