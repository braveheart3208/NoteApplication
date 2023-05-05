package com.appsolute.noteapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.plcoding.cleanarchitecturenoteapp.ui.theme.BabyBlue
import com.plcoding.cleanarchitecturenoteapp.ui.theme.LightGreen
import com.plcoding.cleanarchitecturenoteapp.ui.theme.RedOrange

/**
 * Created by Alex Toan Duong on 4/05/2023.
 * This project belongs to Alex Toan Duong.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, BabyBlue)
    }
}
