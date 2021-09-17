package com.susheelkaram.matrimonyapp.data.remote

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
data class MatchUsersResponse(
    val info: Info,
    val results: List<Result>
)

data class Info(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String
)

data class Result(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
)

data class Dob(
    val age: Int,
    val date: String
)

data class Location(
    val city: String,
    val country: String,
    val state: String,
    val street: Street,
)

data class Login(
    val uuid: String
)

data class Name(
    val first: String,
    val last: String,
    val title: String
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)

data class Street(
    val name: String,
    val number: Int
)
