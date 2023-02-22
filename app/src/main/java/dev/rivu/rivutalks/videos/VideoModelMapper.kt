package dev.rivu.rivutalks.videos

import dev.rivu.rivutalks.common.remote.models.ContentType
import dev.rivu.rivutalks.common.remote.models.VideoModel
import dev.rivu.rivutalks.mapper.UIModelMapper

object VideoModelMapper: UIModelMapper<VideoModel, VideoUIModel> {
    override fun mapToUIModel(dataModel: VideoModel): VideoUIModel {
        return VideoUIModel(
            url = dataModel.url,
            embedUrl = dataModel.embedUrl,
            title = dataModel.title,
            cover = dataModel.cover,
            description = dataModel.description,
            featured = dataModel.featured,
            contentType = when (dataModel.type) {
                ContentType.VIDEO -> VideoUIModel.ContentType.VIDEO
                ContentType.CHANNEL -> VideoUIModel.ContentType.CHANNEL
            }
        )
    }

}