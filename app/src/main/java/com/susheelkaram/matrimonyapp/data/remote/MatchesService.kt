package com.susheelkaram.matrimonyapp.data.remote

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
interface MatchesService {
    @GET("/api")
    fun getMatchesList(
        @Query("results") count: Int = 10,
        @Query("seed") seedText: String = "",
        @Query("nat") nationalities: String = "gp,us,ca,au,nz"
    ): Observable<MatchUsersResponse>
}