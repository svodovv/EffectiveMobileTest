package com.example.effectivemobiletest.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy")
data class VacancyEntity(
    @PrimaryKey val id: String,
    @ColumnInfo("looking_number") val lookingNumber: Int?,
    @ColumnInfo("is_favorite") val isFavorite: Boolean,
    val title: String,
    val address: String,
    val company: String,
    @ColumnInfo("experience_text") val experienceText: String,
    @ColumnInfo("published_date") val publishedDate: String,
)
