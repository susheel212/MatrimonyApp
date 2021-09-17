package com.susheelkaram.matrimonyapp.data.local

import com.susheelkaram.matrimonyapp.data.MatchesDataSource
import com.susheelkaram.matrimonyapp.data.local.db.MatchesDao
import com.susheelkaram.matrimonyapp.data.model.MatchUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
class MatchesLocalDataSource @Inject constructor(private val matchesDao: MatchesDao) :
    MatchesDataSource {
    override fun getMatches(matchesCount: Int): Observable<List<MatchUser>> {
        return matchesDao.getMatches()
    }

    override fun updateInvitationStatus(
        invitationStatus: Int,
        matchUser: MatchUser
    ): Single<Int> {
        return matchesDao.updateInvitationStatus(matchUser.copy(invitationStatus = invitationStatus))
    }

    fun getMatchesCount(): Single<Int> {
        return matchesDao.getMatchesCount()
    }

    fun saveMatches(users: List<MatchUser>) {
        matchesDao.deleteAll().subscribe { it ->
            matchesDao.insertAll(users).subscribe()
        }
    }
}