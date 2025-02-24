package com.application.movietime.data.api.request

data class Member(
    val fullName: String = "",
    val nickName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val gender: String = "",
    val forceDelete: Boolean = false
)

data class MemberPhoto(
    val profilePhoto: String = ""
)