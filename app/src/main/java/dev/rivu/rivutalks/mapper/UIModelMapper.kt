package dev.rivu.rivutalks.mapper

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

interface UIModelMapper<DataModel, UIModel> {
    fun mapListToUIModel(list: List<DataModel>): ImmutableList<UIModel> =
        list.map { dataModel ->
            mapToUIModel(dataModel)
        }.toImmutableList()

    fun mapToUIModel(dataModel: DataModel): UIModel
}