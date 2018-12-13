package com.example.gl.demo_android.paging.positionaldatasource

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PersonDao {
    @Insert
    fun insertPerson(person: Person)

    @Insert
    fun insertPersons(persons: List<Person>)

    @Delete
    fun deletePerson(person: Person)

    @Query("SELECT * FROM Person ORDER BY name COLLATE NOCASE ASC")
    fun getAllPersons(): DataSource.Factory<Int, Person>
}