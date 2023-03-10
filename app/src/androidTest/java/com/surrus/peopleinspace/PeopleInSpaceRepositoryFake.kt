package com.surrus.peopleinspace

import dev.rivu.rivutalks.common.remote.Assignment
import dev.rivu.rivutalks.common.remote.IssPosition
import dev.rivu.rivutalks.common.repository.PeopleInSpaceRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PeopleInSpaceRepositoryFake : PeopleInSpaceRepositoryInterface {
    val peopleList = listOf(
        Assignment("Apollo 11", "Neil Armstrong"),
        Assignment("Apollo 11", "Buzz Aldrin")
    )

    val issPosition = IssPosition(53.2743394, -9.0514163)

    override fun fetchPeopleAsFlow(): Flow<List<Assignment>> {
        return flowOf(peopleList)
    }

    override fun pollISSPosition(): Flow<IssPosition> {
        return flowOf(issPosition)
    }

    override suspend fun fetchPeople(): List<Assignment> {
        return emptyList()
    }

    override suspend fun fetchAndStorePeople() {
    }
}
