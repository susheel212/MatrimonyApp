package com.susheelkaram.matrimonyapp.data.local.db

import androidx.room.*
import com.susheelkaram.matrimonyapp.data.model.MatchUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
@Dao
interface MatchesDao {
    @Query("SELECT * FROM ${DbConstants.TABLE_MATCHES}")
    fun getMatches(): Observable<List<MatchUser>>

    @Query("SELECT COUNT(*) FROM ${DbConstants.TABLE_MATCHES}")
    fun getMatchesCount(): Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(matchUser: MatchUser): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(matchUser: List<MatchUser>): Single<List<Long>>

    @Delete
    fun delete(matchUser: MatchUser): Single<Int>

    @Query("DELETE FROM ${DbConstants.TABLE_MATCHES}")
    fun deleteAll(): Single<Int>

    @Update
    fun updateInvitationStatus(newMatchUser: MatchUser): Single<Int>
}