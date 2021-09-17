package com.susheelkaram.matrimonyapp.data

import com.susheelkaram.matrimonyapp.data.local.MatchesLocalDataSource
import com.susheelkaram.matrimonyapp.data.model.MatchUser
import com.susheelkaram.matrimonyapp.data.remote.MatchesRemoteDataSource
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
class MatchesRepository @Inject constructor(
    private val localDataSource: MatchesLocalDataSource,
    private val remoteDataSource: MatchesRemoteDataSource
) {
    fun getMatchesFromCache(count: Int): Observable<List<MatchUser>> {
        return localDataSource.getMatches(count)
    }

    fun getMatchesList(count: Int): Observable<List<MatchUser>> {
        return remoteDataSource.getMatches(count)
            .flatMap{
                (localDataSource as MatchesLocalDataSource).saveMatches(it)
                localDataSource.getMatches(count)
            }
    }

    fun updateAcceptanceStatus(acceptanceStatus: Int, matchUser: MatchUser): Single<Int> {
        return localDataSource.updateInvitationStatus(
            matchUser = matchUser,
            invitationStatus = acceptanceStatus
        )
    }
}