package com.non.dozortest.api

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ApiIsolationTests : TestCase() {


    @Test
    fun  test_movie_Api_List_Result_Success(){
        val api = TestImplementation.provideApi()
        val test = runBlocking {
            api.getPopularMovies("en", 1)
        }
        assertEquals(test.isSuccessful, true)
    }

    @Test
    fun  test_movie_Api_List_Result_NotEmpty(){
        val api = TestImplementation.provideApi()
        val test = runBlocking {
            api.getPopularMovies("en", 1)
        }
        assertEquals(test.body()?.results?.isNotEmpty(), true)
    }
}