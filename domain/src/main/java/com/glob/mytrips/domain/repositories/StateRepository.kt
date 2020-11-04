package com.glob.mytrips.domain.repositories

import com.glob.mytrips.domain.dtos.StateDto
import io.reactivex.Completable
import io.reactivex.Single

interface StateRepository {
    // TODO: 08/09/2020 in the future you could see the information from your friends
    fun getStatesByUser(idCountry: Int): Single<List<StateDto>>
    fun getStateByID(idCountry: Int): Single<StateDto>
    fun saveStates(states: List<StateDto>): Completable
}