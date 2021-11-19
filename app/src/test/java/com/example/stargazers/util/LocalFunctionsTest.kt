package com.example.stargazers.util

import org.junit.Assert.*
import org.junit.Test

class LocalFunctionsTest {

    @Test
    fun testCompareFunctionResultEquals() {

        // Declare variables for function test
        val a = 30
        val b = 30

        // Call your function
        val result = compareNumbers(a, b)

        // Assert results
        assertEquals(result.aGreater, false)
        assertEquals(result.bGreater, false)
        assertEquals(result.equal, true)

    }

    @Test
    fun testCompareFunctionResultGreater() {

        // Declare variables for function test
        val a = 40
        val b = 30

        // Call your function
        val result = compareNumbers(a, b)

        // Assert results
        assertEquals(result.aGreater, true)
        assertEquals(result.bGreater, false)
        assertEquals(result.equal, false)

    }

    @Test
    fun testCompareFunctionResultSmaller() {

        // Declare variables for function test
        val a = 20
        val b = 30

        // Call your function
        val result = compareNumbers(a, b)

        // Assert results
        assertEquals(result.aGreater, false)
        assertEquals(result.bGreater, true)
        assertEquals(result.equal, false)

    }



}