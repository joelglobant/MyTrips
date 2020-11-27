package com.glob.mytrips.domain.usecases.photo

import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.repositories.PhotoRepository
import com.glob.mytrips.domain.usecases.GetPhotosUseCase
import com.glob.mytrips.domain.usecases.ImmediateExecutorThread
import com.glob.mytrips.domain.usecases.mocks.MyTripsMocks
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class GetPhotosByPlaceUseCaseTest {

    @Mock
    lateinit var photoRepository: PhotoRepository

    @Mock
    lateinit var postExecutorThread: PostExecutorThread

    private val photoByPlaceUseCase: GetPhotosUseCase by lazy {
        GetPhotosUseCase(photoRepository, ImmediateExecutorThread(), postExecutorThread)
    }

    @Before
    fun setup() {
        Mockito.`when`(postExecutorThread.getScheduler()).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun `validate get Photos By IdPlace`() {
        val params = GetPhotosUseCase.Params(1)
        val tripMock = MyTripsMocks
        Mockito.`when`(photoRepository.getPhotos(1))
            .thenReturn(Single.just(listOf(tripMock.photoMock)))
        photoByPlaceUseCase.execute(params)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue {
                it.first().id == tripMock.photoMock.id
            }.assertValue {
                it.first().path == tripMock.photoMock.path
            }.assertValue {
                it.first().idPlace == tripMock.photoMock.idPlace
            }
    }

    @Test
    fun `validate Message Error when Photos Not Registered`() {
        val params = GetPhotosUseCase.Params(3)
        val message = "Item not Found"
        Mockito.`when`(photoRepository.getPhotos(3))
            .thenReturn(Single.error(Throwable(message)))
        photoByPlaceUseCase.execute(params)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

    @Test
    fun `validate message Error With null parameters`() {
        val message = "Photos not found"
        photoByPlaceUseCase.execute(null)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

}