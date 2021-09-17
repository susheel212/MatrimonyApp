package com.susheelkaram.matrimonyapp.data

import com.susheelkaram.matrimonyapp.data.model.MatchUser
import com.susheelkaram.matrimonyapp.data.remote.Result

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
class DataUtils {
    companion object {
        fun getMatchUserFromResult(result: Result) : MatchUser {
            return MatchUser(
                uuid = result.login.uuid,
                name = "${result.name.first} ${result.name.last}",
                age = result.dob.age,
                location = "${result.location.city}, ${result.location.state}",
                gender = result.gender,
                profileImageUrl = result.picture.large
            )
        }
    }
}