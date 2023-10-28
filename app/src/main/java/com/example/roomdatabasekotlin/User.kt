package com.example.roomdatabasekotlin

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val reg_no: Int,
    val name: String,
    val course: String,
    val dob:Int,
    val fathername:String,
    val entrydate:Int
):Parcelable

