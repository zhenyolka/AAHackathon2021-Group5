package com.example.thingder.data.usecases

import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.entities.User
import com.example.thingder.domain.usecases.IFetchMyThingsLikedByOthersUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchMyThingsLikedByOthersUseCase: IFetchMyThingsLikedByOthersUseCase {
    override fun fetch(): Flow<List<Pair<User, Thing>>> {
        return flow {
            emit(listOf(
                Pair(User(1, "prock@mail.ru"), Thing(1, "A nice hat")),
                Pair(User(2, "newuser@mail.ru"), Thing(2, "Old pair of skies")),
                Pair(User(3, "vfock@ya.ru"), Thing(1, "A nice hat")),
                Pair(User(3, "vfock@ya.ru"), Thing(3, "Big umbrella")),
                Pair(User(4, "newsru@news.ru"), Thing(4, "A nice hat"))
            ))
        }
    }
}