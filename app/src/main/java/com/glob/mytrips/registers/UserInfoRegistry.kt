package com.glob.mytrips.registers

import android.content.Context
import com.glob.mytrips.contracts.MainMenuContract
import com.glob.mytrips.data.data.*
import com.glob.mytrips.data.executors.JobExecutor
import com.glob.mytrips.data.executors.UIThread
import com.glob.mytrips.data.local.*
import com.glob.mytrips.data.local.db.MyTripsDb
import com.glob.mytrips.data.mappers.datatodto.*
import com.glob.mytrips.data.mappers.entitytodata.*
import com.glob.mytrips.data.mappers.responsetodata.*
import com.glob.mytrips.data.providers.UserInfoDataProvider
import com.glob.mytrips.data.remote.*
import com.glob.mytrips.data.remote.services.*
import com.glob.mytrips.data.repositories.*
import com.glob.mytrips.data.repositories.datastore.cache.*
import com.glob.mytrips.data.repositories.datastore.remote.*
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.providers.UserInfoProvider
import com.glob.mytrips.domain.repositories.*
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
    private val stateEntityToData = StateEntityToDataMapper()
    private val placeEntityToDto = PlaceEntityToDataMapper()
    private val photoEntityToData = PhotoEntityToDataMapper()

    private val countryDataToDto = CountryDataToDtoMapper()
    private val userDataToDto = UserDataToDtoMapper()
    private val stateDataToDto = StateDataToDtoMapper()
    private val placeDataToDto = PlaceDataToDtoMapper()
    private val photoDataToDto = PhotoDataToDtoMapper()


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
    // TODO: 03/11/2020 add preferences!!!
    private val countryCache: CountryCache by lazy {
        CountryCacheImpl(dataBase)
    }
    private val stateCache: StateCache by lazy {
        StateCacheImp(dataBase)
    }
    private val placeCache: PlaceCache by lazy {
        PlaceCacheImp(dataBase)
    }
    private val photoCache: PhotoCache by lazy {
        PhotoCacheImp(dataBase)
    }

    /**----- DataStore ----*/
    private val remoteDataStore: RemoteDataStore = RemoteDataStore(userRemote, userRespToEntity)
    private val localUserCache: LocalDataStore = LocalDataStore(userCache, userEntityToDto)

    private val countryRemoteDataStore: CountryRemoteDataStore =
        CountryRemoteDataStore(countryRemote, countryRespToDataMap)
    private val countryCacheDataStore: CountryCacheDataStore =
        CountryCacheDataStore(countryCache, countryDataToDto, countryEntityToDataMap)

    private val stateRemoteDataStore: StateRemoteDataStore =
        StateRemoteDataStore(stateRemote, stateRespToDataMap)
    private val stateCacheDataStore: StateCacheDataStore =
        StateCacheDataStore(stateCache, stateEntityToData, stateDataToDto)

    private val placeRemoteDataStore: PlaceRemoteDataStore =
        PlaceRemoteDataStore(placeRemote, placeRespToDataMap)
    private val placeCacheDataStore: PlaceCacheDataStore =
        PlaceCacheDataStore(placeCache, placeDataToDto, placeEntityToDto)

    private val photoRemoteDataStore: PhotoRemoteDataStore =
        PhotoRemoteDataStore(photoRemote,photoRespToDataMap)
    private val photoCacheDataStore: PhotoCacheDataStore =
        PhotoCacheDataStore(photoCache, photoEntityToData, photoDataToDto)


    /** ------ Factories ------*/
    private val stateFactory: StateDataStoreFactory by lazy {
        StateDataStoreFactoryImp(stateCache, stateRemoteDataStore, stateCacheDataStore)
    }
    private val userFactory: UserInfoDataStoryFactory by lazy {
        UserInfoDataStoreFactoryImpl(userCache, remoteDataStore, localUserCache)
    }
    private val countryFactory: CountryDataStoreFactory by lazy {
        CountryDataStoreFactoryImpl(countryCache, countryRemoteDataStore, countryCacheDataStore)
    }
    private val placeFactory: PlaceDataStoreFactory by lazy {
        PlaceDataStoreFactoryImp(placeCache, placeRemoteDataStore, placeCacheDataStore)
    }
    private val photoFactory: PhotoDataStoreFactory by lazy {
        PhotoDataStoreFactoryImp(photoCache, photoRemoteDataStore, photoCacheDataStore)
    }

    /**-------  Repositories  ---------- */
    private val photoRepository: PhotoRepository by lazy {
        PhotoDataRepository(photoFactory, photoDataToDto)
    }
    private val placeRepository: PlaceRepository by lazy {
        PlaceDataRepository(placeFactory, photoRepository, placeDataToDto, photoDataToDto)
    }
    private val stateRepository: StateRepository by lazy {
        StateDataRepository(stateFactory, placeRepository, stateDataToDto, placeDataToDto)
    }

    private val countryRepository: CountryRepository by lazy {
        CountryDataRepository(countryFactory, stateRepository, countryDataToDto, stateDataToDto)
    }

    private val userRepository: UserInfoRepository by lazy {
        UserInfoDataRepository(
            userFactory,
            countryRepository,
            userDataToDto,
            countryDataToDto,
            userEntityToDataMap
        )
    }

    private val provider: UserInfoProvider by lazy {
        UserInfoDataProvider(userRepository, threadExecutor, postExecutorThread)
    }

    fun provide(view: MainMenuContract.View): MainMenuContract.Presenter {
        return MainMenuPresenter(provider, view, mapperModel)
    }
}