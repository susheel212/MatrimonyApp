package com.susheelkaram.matrimonyapp.data

import com.susheelkaram.matrimonyapp.data.model.MatchUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
interface MatchesDataSource {
    fun getMatches(matchesCount: Int): Observable<List<MatchUser>>
    fun updateInvitationStatus(invitationStatus: Int, matchUser: MatchUser): Single<Int>
}