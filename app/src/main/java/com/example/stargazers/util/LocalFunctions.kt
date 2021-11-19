package com.example.stargazers.util

import android.util.Log


internal fun compareNumbers(a: Int, b: Int): CompareNumbersResult {


    if (a > b) {
        return CompareNumbersResult(aGreater = true, bGreater = false, equal = false)
    } else if (a < b) {
        return CompareNumbersResult(aGreater = false, bGreater = true, equal = false)
    } else {
        return CompareNumbersResult(aGreater = false, bGreater = false, equal = true)
    }
}


data class CompareNumbersResult(
    val aGreater: Boolean,
    val bGreater: Boolean,
    val equal: Boolean
)

