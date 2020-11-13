package com.glob.mytrips.registers

import android.content.Context
import com.glob.mytrips.contracts.CountryListContract
import com.glob.mytrips.data.data.*
import com.glob.mytrips.data.executors.JobExecutor
import com.glob.mytrips.data.executors.UIThread
import com.glob.mytrips.data.local.*
import com.glob.mytrips.data.local.db.MyTripsDb
import com.glob.mytrips.data.mappers.datatodto.*
import com.glob.mytrips.data.mappers.entitytodata.*
import com.glob.mytrips.data.mappers.responsetodata.*
import com.glob.mytrips.data.providers.CountryDataProvider
import com.glob.mytrips.data.remote.*
import com.glob.mytrips.data.remote.services.*
import com.glob.mytrips.data.repositories.CountryDataRepository
import com.glob.mytrips.data.repositories.PhotoDataRepository
import com.glob.mytrips.data.repositories.PlaceDataRepository
import com.glob.mytrips.data.repositories.StateDataRepository
import com.glob.mytrips.data.repositories.datastore.cache.*
import com.glob.mytrips.data.repositories.datastore.remote.*
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.providers.CountryProvider
import com.glob.mytrips.domain.repositories.CountryRepository
import com.glob.mytrips.domain.repositories.PhotoRepository
import com.glob.mytrips.domain.repositories.PlaceRepository
import com.glob.mytrips.domain.repositories.StateRepository
import com.glob.mytrips.models.mappers.CountryMapperModel
import com.glob.mytrips.presenters.CountryListPresenter
import com.glob.mytrips.services.RetrofitFactory

class CountryRegistry(context: Context) {

    /** ------ Mappers --------- */
    private val photoRespToDataMap = PhotoResponseToDataMapper()
    private val placeRespToDataMap = PlaceResponseToDataMapper(photoRespToDataMap)
    private val stateRespToDataMap = StateResponseToDataMapper(placeRespToDataMap)
    private val countryRespToDataMap = CountryResponseToDataMapper(stateRespToDataMap)

    private val countryEntityToDataMap = CountryEntityToDataMapper()
    private val stateEntityToData = StateEntityToDataMapper()
    private val placeEntityToDto = PlaceEntityToDataMapper()
    private val photoEntityToData = PhotoEntityToDataMapper()

    private val countryDataToDto = CountryDataToDtoMapper()
    private val stateDataToDto = StateDataToDtoMapper()
    private val placeDataToDto = PlaceDataToDtoMapper()
    private val photoDataToDto = PhotoDataToDtoMapper()

    private val countryToMoodel = CountryMapperModel()


}