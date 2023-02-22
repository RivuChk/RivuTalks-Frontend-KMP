package dev.rivu.rivutalks.aboutme.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AboutMeScreen(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "AboutMe Screen",
        style = MaterialTheme.typography.labelMedium,
        color = Color.White,
        modifier = modifier
    )
}