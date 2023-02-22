package dev.rivu.rivutalks.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.ramcosta.composedestinations.spec.NavHostEngine
import dev.rivu.rivutalks.NavGraphs
import dev.rivu.rivutalks.R
import dev.rivu.rivutalks.destinations.AboutMeScreenDestination
import dev.rivu.rivutalks.destinations.BlogsScreenDestination
import dev.rivu.rivutalks.destinations.DirectionDestination
import dev.rivu.rivutalks.destinations.VideosScreenDestination
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.delay
import okhttp3.internal.immutableListOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RivuTalksAppMainScreen(
) {
    val destinationItem = remember {
        mutableStateOf(videoDestinationItem)
    }

    val engine: NavHostEngine = rememberNavHostEngine()
    val navController: NavHostController = engine.rememberNavController()

    LaunchedEffect(destinationItem.value.id) {
        navController.navigate(destinationItem.value.destination)
    }

    RivuTalksAppTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(stringResource(R.string.app_name))
                    }
                )
            },
            bottomBar = {
                BottomBar(
                    currentItemState = destinationItem
                )
            }
        ) { contentPadding ->
            Surface(modifier = Modifier.padding(contentPadding)) {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    engine = engine,
                    navController = navController
                )
            }
        }

    }
}

@Composable
fun BottomBar(
    currentItemState: MutableState<BottomNavigationItem>
) {
    BottomAppBar {
        for (item in navigationItems) {
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(item.logo),
                        contentDescription = item.label,
                        tint = Color.Unspecified,
                    )
                },
                selected = item.id == currentItemState.value.id,
                onClick = {
                    currentItemState.value = item
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            )
        }
    }
}

data class BottomNavigationItem (
    val destination: DirectionDestination,
    @DrawableRes val logo: Int,
    val label: String,
    val id: Int
)

val navigationItems by lazy {
    immutableListOf(
        videoDestinationItem,
        blogsDestinationItem,
        aboutMeDestinationItem,
    )
}

val videoDestinationItem = BottomNavigationItem(
    destination = VideosScreenDestination,
    logo = R.drawable.ic_ondemand_video,
    label = "Videos",
    id = 0,
)

val blogsDestinationItem = BottomNavigationItem(
    destination = BlogsScreenDestination,
    logo = R.drawable.ic_article,
    label = "Blogs",
    id = 1,
)

val aboutMeDestinationItem = BottomNavigationItem(
    destination = AboutMeScreenDestination,
    logo = R.drawable.ic_info,
    label = "About Me",
    id = 2,
)