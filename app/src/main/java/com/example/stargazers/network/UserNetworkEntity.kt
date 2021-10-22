package com.example.stargazers.network

import com.squareup.moshi.Json

data class UserNetworkEntity (

        val id: Int,
        val login: String,
        @Json(name = "avatar_url")
        val imgSrcUrl: String,
        val type: String,
        @Json(name = "site_admin")
        val siteAdmin: Boolean
        )