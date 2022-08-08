package com.example.assignment.data.api

import junit.framework.TestCase
import org.junit.Test

class RetrofitBuilderTest : TestCase() {
    @Test
    fun testGetApiService() {
        val service = RetrofitBuilder.getApiService()
        assertNotNull(service)
    }
}