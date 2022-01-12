package com.example.stargazers.model

data class Contact(
    val id: String,
    val name: String
) {
    var numbers = ArrayList<String>()
    var emails = ArrayList<String>()
}
