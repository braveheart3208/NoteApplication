package com.appsolute.noteapplication.domain.model

import com.appsolute.noteapplication.domain.OrderType

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
sealed class NoteOrder(val orderType: OrderType) {
    class ByTitle(orderType: OrderType) : NoteOrder(orderType)
    class ByDate(orderType: OrderType) : NoteOrder(orderType)

    fun copy(orderType: OrderType): NoteOrder {
        return when (this) {
            is ByTitle -> ByTitle(orderType)
            is ByDate -> ByDate(orderType)
        }
    }
}
