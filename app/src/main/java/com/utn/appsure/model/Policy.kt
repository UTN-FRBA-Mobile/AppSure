package com.utn.appsure.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Policy(
    @PrimaryKey
    val license: String,
    val brand: String,
    val model: String,
    val year: Int,
    val colour: String,
    val poster: Int?,
    val lat: Double,
    val lon: Double
) {
    override fun toString(): String {
        return "Policy(license='$license', brand='$brand', model='$model', year=$year, colour='$colour', poster=$poster)"
    }
}