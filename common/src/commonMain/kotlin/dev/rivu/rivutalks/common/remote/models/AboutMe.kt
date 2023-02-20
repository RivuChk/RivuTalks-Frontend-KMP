package dev.rivu.rivutalks.common.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class AboutMe(
    val isSuccess: Boolean,
    val headline: String,
    val aboutMeDetails: String,
)
