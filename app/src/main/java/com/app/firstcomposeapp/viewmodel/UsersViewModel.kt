package com.app.firstcomposeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.firstcomposeapp.models.Data
import com.app.firstcomposeapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val users: StateFlow<List<Data>>
        get() = userRepository.users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {

            userRepository.getUsers()
        }
    }
}
