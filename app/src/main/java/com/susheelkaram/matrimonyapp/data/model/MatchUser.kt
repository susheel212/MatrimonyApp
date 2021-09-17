package com.susheelkaram.matrimonyapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.susheelkaram.matrimonyapp.data.local.db.DbConstants

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
@Entity(tableName = DbConstants.TABLE_MATCHES)
data class MatchUser(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id")
    var id: Long = 0,
    val uuid: String,
    val name: String,
    val profileImageUrl: String,
    val age: Int,
    val location: String,
    val gender: String,
    val invitationStatus: Int = InvitationStatus.NONE,
)

object InvitationStatus {
    const val NONE = -1
    const val REJECTED = 0
    const val ACCEPTED = 1
}
