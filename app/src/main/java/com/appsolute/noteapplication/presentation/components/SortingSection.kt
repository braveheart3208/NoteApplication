package com.appsolute.noteapplication.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appsolute.noteapplication.domain.OrderType
import com.appsolute.noteapplication.domain.model.NoteOrder

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

@Composable
fun SortingSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.ByDate(OrderType.Descending),
    onSortingOptionChange: (NoteOrder) -> Unit
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            ComposableDefaultRadioButton(
                text = "Title",
                isChecked = noteOrder is NoteOrder.ByTitle,
                onCheck = {
                    onSortingOptionChange(NoteOrder.ByTitle(noteOrder.orderType))
                })
            Spacer(modifier = Modifier.width(9.dp))

            ComposableDefaultRadioButton(
                text = "Date",
                isChecked = noteOrder is NoteOrder.ByDate,
                onCheck = {
                    onSortingOptionChange(NoteOrder.ByDate(noteOrder.orderType))
                })

            Spacer(modifier = Modifier.width(9.dp))
        }
        Spacer(modifier = Modifier.width(9.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            ComposableDefaultRadioButton(
                text = "Ascending",
                isChecked = noteOrder.orderType is OrderType.Ascending,
                onCheck = {
                    onSortingOptionChange(noteOrder.copy(OrderType.Ascending))
                })
            Spacer(modifier = Modifier.width(9.dp))

            ComposableDefaultRadioButton(
                text = "Descending",
                isChecked = noteOrder.orderType is OrderType.Descending,
                onCheck = {
                    onSortingOptionChange(noteOrder.copy(OrderType.Descending))
                })

            Spacer(modifier = Modifier.width(9.dp))
        }
    }
}