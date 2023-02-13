package com.intelligent.sportman.data.remote.repository

import com.intelligent.sportman.data.remote.api.SportApi
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

/**
 * Implementation of mock api Repository that fetch data
 */
@ViewModelScoped
class SportRepository @Inject constructor(
    private val sportApi: SportApi
){
    /**
     * Get all sports data
     */
    suspend fun getSports() = sportApi.getSports()
}