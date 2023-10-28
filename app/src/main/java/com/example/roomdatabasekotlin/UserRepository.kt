package com.example.roomdatabasekotlin

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()
    fun addUser(user: User) {
        userDao.addUser(user)
    }

    fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }
}