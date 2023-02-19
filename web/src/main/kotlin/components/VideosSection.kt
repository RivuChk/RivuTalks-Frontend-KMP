package components

import AppDependenciesContext
import components.materialui.VideoList
import dev.rivu.rivutalks.common.remote.models.VideoModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import react.RProps
import react.functionalComponent
import react.useContext
import react.useEffectWithCleanup
import react.useState

val VideosSection = functionalComponent<RProps> {
    val appDependencies = useContext(AppDependenciesContext)
    val rivutalksRepository = appDependencies.rivutalksRepository

    val (videos, setVideos) = useState(emptyList<VideoModel>())
    val (selectedVideo, setSelectedVideo) = useState<VideoModel?>(null)

    useEffectWithCleanup(dependencies = listOf()) {
        val mainScope = MainScope()

        mainScope.launch {
            val videos = rivutalksRepository.fetchVideoContents()
            setVideos(videos)
        }
        return@useEffectWithCleanup { mainScope.cancel() }
    }

    VideoList(
        videos = videos,
        selectedVideo = selectedVideo,
        onSelect = {
            setSelectedVideo(it)
        }
    )

}