package com.example.thingder.data.usecases.tinder

import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.tinder.IFetchNearbyThingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchNearbyThingUseCase: IFetchNearbyThingUseCase {
    override fun fetch(): Flow<List<Thing>> {
        return flow {
            emit(
                    listOf(
                            Thing(
                                    id = 1,
                                    title = "Brick"
                            ),
                            Thing(
                                    id = 2012,
                                    title = "End of The World Button"
                            ),
                            Thing(
                                    id = 42,
                                    title = "Universe"
                            )
                    )
            )
        }
    }
}