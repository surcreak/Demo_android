package com.example.gl.demo_android.paging.positionaldatasource

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Person(@PrimaryKey(autoGenerate = true) val id: Int, val name: String)