package com.intelligent.sportman.data.remote.api

import com.intelligent.sportman.model.Sport
import retrofit2.http.GET


/**
 * A public interface that exposes fetch methods from a mock kaizen api
 */
interface SportApi {

    @GET("sports")
    suspend fun getSports(): List<Sport>

}