package dev.rivu.rivutalks.videos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.rivu.rivutalks.common.repository.RivuTalksRepository
import dev.rivu.rivutalks.common.repository.RivuTalksRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VideosViewModel constructor(
    val repository: RivuTalksRepositoryInterface
) : ViewModel() {
    var state by mutableStateOf<VideosState>(VideosState.Empty)
        private set

    fun loadVideos() {
        state = VideosState.Loading
        viewModelScope.launch {
            val videos = repository.fetchVideos()
            withContext(Dispatchers.Main) {
                state = VideosState.Success(
                    VideoModelMapper.mapListToUIModel(
                        videos
                    )
                )
            }
        }
    }
}