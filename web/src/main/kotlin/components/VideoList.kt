package components

import components.materialui.Card
import components.materialui.CardContent
import components.materialui.CardMedia
import components.materialui.CircularProgress
import components.materialui.List
import components.materialui.ListItem
import dev.rivu.rivutalks.common.remote.models.ContentType.CHANNEL
import dev.rivu.rivutalks.common.remote.models.ContentType.VIDEO
import dev.rivu.rivutalks.common.remote.models.VideoModel
import kotlinx.css.em
import react.RBuilder
import react.dom.a


fun RBuilder.VideoList(
    videos: List<VideoModel>,
    selectedVideo: VideoModel?,
    onSelect: (VideoModel) -> Unit
) {
    List {
        if (videos.isEmpty()) {
            CircularProgress {

            }
        } else {
            videos.forEach { video ->
                ListItem {
                    Card {
                        when(video.type) {
                            CHANNEL -> {
                                CardContent {
                                    attrs {
                                        width = 100.em
                                        //onSelect(item)
                                    }
                                    a(href = video.url, target = "_blank") {
                                        Typography("h4", video.title)
                                        Typography("body2", video.description ?: "No description")
                                    }
                                }
                            }
                            VIDEO -> {
                                CardMedia {
                                    if (video.embedUrl.isNullOrBlank()) {
                                        attrs {
                                            component="img"
                                            src = video.cover
                                            title = video.title
                                            height = 500.em
                                            width = 500.em
                                        }
                                    } else {
                                        attrs {
                                            component="iframe"
                                            src = video.embedUrl
                                            title = video.title
                                            height = 500.em
                                            width = 500.em
                                        }
                                    }
                                }
                                CardContent {
                                    attrs {
                                        width = 500.em
                                    }
                                    Typography("h4", video.title)
                                    Typography("body2", video.description ?: "No description")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}