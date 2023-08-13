package com.example.currencyconversionapp.utils

import org.junit.Test
import org.junit.Assert.*
class ResponseUnitTest {

    @Test
    fun loadingStateTest(){
        val loadingResponse: Response<Int> = Response.Loading
        assertEquals(loadingResponse, Response.Loading)
    }

    @Test
    fun errorStateTest() {
        val throwable = Throwable("error")
        val errorResponse: Response<Int> = Response.Error(throwable)

        assertEquals(errorResponse, Response.Error(throwable))
    }

    @Test
    fun successStateTest() {
        val successResponse: Response<String> = Response.Success("success")

        assertEquals(successResponse, Response.Success("success"))
    }
}