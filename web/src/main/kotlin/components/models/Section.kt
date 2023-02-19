package components.models

import dev.rivu.rivutalks.common.remote.models.Blog
import dev.rivu.rivutalks.common.remote.models.VideoModel

sealed class Section(val title: String, val index: Int) {
    object BlogSection : Section("Blogs", 0)
    object VideosSection : Section("Videos and Channels", 1)
    object AboutSection : Section("About Me", 2)
}
