package com.utn.appsure.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.utn.appsure.model.Policy

@Dao
interface PolicyDao {
    @Query("SELECT * FROM policy")
    fun getAll(): List<Policy>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg policies: Policy)

    @Query("SELECT * FROM policy where license=:id limit 1")
    fun getById(id: String): Policy

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(policy: Policy)
}