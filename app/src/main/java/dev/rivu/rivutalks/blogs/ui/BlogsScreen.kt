package dev.rivu.rivutalks.blogs.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ramcosta.composedestinations.annotation.Destination
import dev.rivu.rivutalks.videos.VideosViewModel
import org.koin.androidx.compose.getViewModel

@Destination
@Composable
fun BlogsScreen(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Blogs Screen",
        style = MaterialTheme.typography.labelMedium,
        color = Color.White,
        modifier = modifier
    )
}