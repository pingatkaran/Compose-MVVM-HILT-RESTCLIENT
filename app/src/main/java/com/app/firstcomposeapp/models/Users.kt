package com.app.firstcomposeapp.models

data class Users(
    val data: List<Data>?=null,
    val page: Int?=null,
    val per_page: Int?=null,
    val support: Support?=null,
    val total: Int?=null,
    val total_pages: Int?=null
)