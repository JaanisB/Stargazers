package com.example.stargazers.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    val id: Int,
    val login: String,
    val imgSrcUrl: String,
    val type: String,
    val siteAdmin: Boolean
    ) : Parcelable