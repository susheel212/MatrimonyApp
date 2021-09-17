package com.susheelkaram.matrimonyapp.data.remote

import android.util.Log
import com.susheelkaram.matrimonyapp.data.DataUtils
import com.susheelkaram.matrimonyapp.data.MatchesDataSource
import com.susheelkaram.matrimonyapp.data.model.MatchUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject
import javax.security.auth.callback.Callback

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
class MatchesRemoteDataSource @Inject constructor(private val matchesService: MatchesService) :
    MatchesDataSource {
    override fun getMatches(matchesCount: Int): Observable<List<MatchUser>> {
        return matchesService.getMatchesList()
            .map {
                it.results.map {
                    DataUtils.getMatchUserFromResult(it)
                }
            };
//            object : retrofit2.Callback<MatchUsersResponse> {
//                override fun onResponse(
//                    call: Call<MatchUsersResponse>,
//                    response: Response<MatchUsersResponse>
//                ) {
//                    Log.d("MatchesRemoteDataSource","got response")
//                }
//
//                override fun onFailure(call: Call<MatchUsersResponse>, t: Throwable) {
//                    Log.d("MatchesRemoteDataSource","got error")
//                }
//
//            }
    }

    override fun updateInvitationStatus(invitationStatus: Int, matchUser: MatchUser): Single<Int> {
        TODO("Not yet implemented")
    }
}