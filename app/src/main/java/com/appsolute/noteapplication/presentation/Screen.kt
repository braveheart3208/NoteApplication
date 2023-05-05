package com.appsolute.noteapplication.presentation

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
sealed class Screen(val route: String) {
    object NoteListScreen : Screen("note_list_screen")
    object NoteDetailScreen : Screen("note_detail_screen")
}