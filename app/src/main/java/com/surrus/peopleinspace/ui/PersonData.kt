package com.surrus.peopleinspace.ui

import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import dev.rivu.rivutalks.common.remote.Assignment

class PersonProvider : CollectionPreviewParameterProvider<Assignment>(
    listOf(
        Assignment("ISS", "Chris Cassidy"),
        Assignment("ISS", "Anatoli Ivanishin")
    )
)
