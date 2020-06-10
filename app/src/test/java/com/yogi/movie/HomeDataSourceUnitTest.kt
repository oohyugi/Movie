package com.yogi.movie

import com.yogi.movie.core.model.BaseMdl
import com.yogi.movie.core.model.MovieMdl
import com.yogi.movie.core.utils.ResultState
import com.yogi.movie.data.source.remote.ApiServices
import com.yogi.movie.data.source.remote.HomeRemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

/**
 * Created by Yogi Putra on 10/06/20.
 * Github : https://github.com/oohyugi
 */

class HomeDataSourceUnitTest {


    private var apiService = mock(ApiServices::class.java)
    private lateinit var source: HomeRemoteDataSource

    private val response = BaseMdl<List<MovieMdl>>()


    @Before
    fun setup() {
        source = HomeRemoteDataSource(apiService)

    }

    @Test
    fun `should home success`() = runBlocking {
        `when`(apiService.getHomeApi("popular", BuildConfig.API_KEY, 1)).thenReturn(
            Response.success(response)
        )

        val repo = source.getHome("popular", 1)

        assertEquals(ResultState.Success(BaseMdl<List<MovieMdl>>()), repo)

    }

    @Test
    fun `should return error`() {
        val actual = ResultState.Error("")
        val result = runBlocking {
            `when`(source.getHome("popular", 1))
                .thenReturn(
                    ResultState.Error("")
                )

            source.getHome("popular", 1)
        }

        // probably has different error message, so you can check by type of java class
        assert(result.javaClass === actual.javaClass)
    }


}
