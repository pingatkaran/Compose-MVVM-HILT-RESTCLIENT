package com.app.firstcomposeapp.repository

import android.util.Log
import com.app.firstcomposeapp.api.UsersAPI
import com.app.firstcomposeapp.models.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UserRepository @Inject constructor(private val usersAPI: UsersAPI) {

    private val _users = MutableStateFlow<List<Data>>(emptyList())
    val users: StateFlow<List<Data>>
        get() = _users

    suspend fun getUsers() {
        val response = usersAPI.getUsers()
        Log.e("TAG", "getUsers:${response.body()} ", )
        if (response.isSuccessful) {
            response.body()?.data?.let {
                _users.emit(it)
            } ?: run {
                // Handle the case where the body is null
                _users.emit(emptyList())
            }
        } else {

            // Handle the case where the response is not successful
            _users.emit(emptyList())
        }
    }
}