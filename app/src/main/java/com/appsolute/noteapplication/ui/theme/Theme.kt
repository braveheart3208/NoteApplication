package com.appsolute.noteapplication.ui.theme

//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.darkColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

//private val DarkColorPalette = darkColors(
//    primary = Color.White,
//    background = DarkGray,
//    onBackground = Color.White,
//    surface = LightBlue,
//    onSurface = DarkGray
//)

@Composable
fun CleanArchitectureNoteAppTheme(darkTheme: Boolean = true, content: @Composable() () -> Unit) {
    MaterialTheme(
        typography = Typography,
        content = content
    )
}