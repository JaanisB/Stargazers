package com.example.stargazers.model

data class User (
    val id: Int,
    val login: String,
    val imgSrcUrl: String,
    val type: String,
    val siteAdmin: Boolean
    )