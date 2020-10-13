package com.glob.mytrips.domain.repositories

import com.glob.mytrips.domain.dtos.StateDto
import io.reactivex.Single

interface StateRepository {
    // TODO: 08/09/2020 in the future you coul see the information from your friends
    fun getStatesByUser(idUser: Int): Single<List<StateDto>>
    fun getStateByID(idState: Int): Single<StateDto>
}