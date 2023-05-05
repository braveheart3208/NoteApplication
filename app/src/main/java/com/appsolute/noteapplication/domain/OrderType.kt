package com.appsolute.noteapplication.domain

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
sealed class OrderType{
    object Ascending : OrderType()
    object Descending : OrderType()
}
