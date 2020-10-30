package com.glob.mytrips.registers

import android.content.Context
import com.glob.mytrips.contracts.MainMenuContract
import com.glob.mytrips.data.data.CountryDataStoreFactory
import com.glob.mytrips.data.data.CountryDataStoreFactoryImpl
import com.glob.mytrips.data.data.UserInfoDataStoreFactoryImpl
import com.glob.mytrips.data.data.UserInfoDataStoryFactory
import com.glob.mytrips.data.executors.JobExecutor
import com.glob.mytrips.data.executors.UIThread
import com.glob.mytrips.data.local.*
import com.glob.mytrips.data.local.db.MyTripsDb
import com.glob.mytrips.data.local.entities.CountryEntity
import com.glob.mytrips.data.mappers.datatodto.CountryDataToDtoMapper
import com.glob.mytrips.data.mappers.datatodto.UserDataToDtoMapper
import com.glob.mytrips.data.mappers.entitytodata.CountryEntityToDataMapper
import com.glob.mytrips.data.mappers.entitytodata.UserEntityToDataMapper
import com.glob.mytrips.data.mappers.responsetodata.*
import com.glob.mytrips.data.providers.UserInfoDataProvider
import com.glob.mytrips.data.remote.*
import com.glob.mytrips.data.remote.services.*
import com.glob.mytrips.data.repositories.CountryDataRepository
import com.glob.mytrips.data.repositories.UserInfoDataRepository
import com.glob.mytrips.data.repositories.datastore.cache.CountryCache
import com.glob.mytrips.data.repositories.datastore.cache.UserCache
import com.glob.mytrips.data.repositories.datastore.remote.*
import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.providers.UserInfoProvider
import com.glob.mytrips.domain.repositories.CountryRepository
import com.glob.mytrips.domain.repositories.UserInfoRepository
import com.glob.mytrips.models.mappers.UserMapperModel
import com.glob.mytrips.presenters.MainMenuPresenter
import com.glob.mytrips.services.RetrofitFactory

class UserInfoRegistry(context: Context) { //todo <<-- is valid have an context her??

    private val threadExecutor: ThreadExecutor by lazy {
        JobExecutor()
    }

    private val postExecutorThread: PostExecutorThread by lazy {
        UIThread()
    }


    /** ------ Services -------- */
    private val getUserService: UserServices by lazy {
        RetrofitFactory.instance().create(UserServices::class.java)
    }
    private val getCountryService: CountryServices by lazy {
        RetrofitFactory.instance().create(CountryServices::class.java)
    }
    private val getStateService: StateServices by lazy {
        RetrofitFactory.instance().create(StateServices::class.java)
    }
    private val getPlaceService: PlaceServices by lazy {
        RetrofitFactory.instance().create(PlaceServices::class.java)
    }
    private val getPhotoService: PhotoServices by lazy {
        RetrofitFactory.instance().create(PhotoServices::class.java)
    }

    /**----- Data Base -------- */
    private val dataBase = MyTripsDb.getInstance(context)


    /** ------ Mappers --------- */
    private val photoRespToDataMap = PhotoResponseToDataMapper()
    private val placeRespToDataMap = PlaceResponseToDataMapper(photoRespToDataMap)
    private val stateRespToDataMap = StateResponseToDataMapper(placeRespToDataMap)

    private val countryRespToDataMap = CountryResponseToDataMapper(stateRespToDataMap)

    private val userRespToEntity = UserResponseToDataMapper(countryRespToDataMap)
    private val userEntityToDto = UserEntityToDataMapper()
    private val userPreferences = PreferencesHelper(context)
    private val mapperModel = UserMapperModel()

    private val countryEntityToDataMap = CountryEntityToDataMapper()
    private val userEntityToDataMap = UserEntityToDataMapper()

    private val countryDataToDto = CountryDataToDtoMapper()
    private val userDataToDto = UserDataToDtoMapper()


    /**-------  Remote implementations ---------**/
    //-----  Remote ---
    private val userRemote: UserRemote by lazy {
        UserRemoteImpl(getUserService)
    }
    private val countryRemote: CountryRemote by lazy {
        CountryRemoteImpl(getCountryService)
    }
    private val stateRemote: StateRemote by lazy {
        StateRemoteImpl(getStateService)
    }
    private val placeRemote: PlacesRemote by lazy {
        PlaceRemoteImpl(getPlaceService)
    }
    private val photoRemote: PhotoRemote by lazy {
        PhotoRemoteImpl(getPhotoService)
    }

    //----- Local ----
    private val userCache: UserCache by lazy {
        UserLocalImpl(dataBase, userPreferences)
    }
    private val countryCache: CountryCache by lazy {
        CountryCacheImpl(dataBase)
    }

    /**----- DataStore ----*/
    private val remoteDataStore: RemoteDataStore = RemoteDataStore(userRemote, userRespToEntity)
    private val localUserCache: LocalDataStore = LocalDataStore(userCache, userEntityToDto)

    private val countryRemoteDataStore: CountryRemoteDataStore =
        CountryRemoteDataStore(countryRemote, countryDataToDto, countryRespToDataMap)

    private val countryCacheDataStore: CountryCacheDataStore =
        CountryCacheDataStore(countryCache, countryDataToDto, countryEntityToDataMap)

    private val userFactory: UserInfoDataStoryFactory by lazy {
        UserInfoDataStoreFactoryImpl(userCache, remoteDataStore, localUserCache)
    }
    private val countryFactory: CountryDataStoreFactory by lazy {
        CountryDataStoreFactoryImpl(countryCache, countryRemoteDataStore, countryCacheDataStore)
    }

    /**-------  Repositories  ---------- */
    private val countryRepository: CountryRepository by lazy {
        CountryDataRepository(
            countryFactory
            //, countryEntityToDataMap,
            //countryDataToDto
        )
    }

    private val userRepository: UserInfoRepository by lazy {
        UserInfoDataRepository(
            userFactory,
            countryRepository,
            userDataToDto,
            userEntityToDataMap,
            countryEntityToDataMap
        )
    }

    private val provider: UserInfoProvider by lazy {
        UserInfoDataProvider(userRepository, threadExecutor, postExecutorThread)
    }

    fun provide(view: MainMenuContract.View): MainMenuContract.Presenter {
        return MainMenuPresenter(provider, view, mapperModel)
    }
}