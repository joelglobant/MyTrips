package com.glob.mytrips.domain.usecases.state

import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.repositories.StateRepository
import com.glob.mytrips.domain.usecases.ImmediateExecutorThread
import com.glob.mytrips.domain.usecases.mocks.MyTripsMocks
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner.StrictStubs::class)
class GetStateByIdUseCaseTest : TestCase() {

    @Mock
    lateinit var stateRepository: StateRepository

    @Mock
    lateinit var postExecutorThread: PostExecutorThread

    private val stateByIdUseCase: GetStateByIdUseCase by lazy {
        GetStateByIdUseCase(
            stateRepository,
            ImmediateExecutorThread(), postExecutorThread
        )
    }

    @Before
    fun setup() {
        Mockito.`when`(postExecutorThread.getScheduler()).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun validateStateSuccess() {

        val params = GetStateByIdUseCase.Params(1)
        Mockito.`when`(stateRepository.getStateByID(1))
            .thenReturn(Single.just(MyTripsMocks().stateMock))
        stateByIdUseCase.execute(params)
            .test()
            .assertComplete()
            .assertNoErrors()
            // TODO: 05/09/2020 this case need to validate every value?
            .assertValue {
                it.id == MyTripsMocks().stateMock.id
            }
            .assertValue {
                it.name == MyTripsMocks().stateMock.name
            }
            .assertValue {
                it.places[0] == MyTripsMocks().placeMock
            }
    }

    @Test
    fun validateGetStateError() {
        val params = GetStateByIdUseCase.Params(1)
        val message = "Item not Found"
        Mockito.`when`(stateRepository.getStateByID(1))
            .thenReturn(Single.error(Throwable(message)))
        stateByIdUseCase.execute(params)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

    @Test
    fun validateGetStateFail() {
        val message = "Empty State list"
        stateByIdUseCase.execute(null)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

    @Spy
    var spyList: ArrayList<String> = ArrayList()


    @Test
    fun doSpyMockitoTest() {

        spyList.add("one")
        spyList.add("two")

        Mockito.verify(spyList).add("one")
        Mockito.verify(spyList).add("two")

        //assertEquals(2, spyList.size)

        //objTest = SpyTest()
        val spyClass = Mockito.spy(SpyTest())
        //Mockito.`when`(objTest.doAnOperation()).thenReturn(3)
//        Mockito.doNothing().`when`(objTest).doAnOperation()
//        doReturn(3).`when`(objTest.doAnOperation())
//        assertEquals(3, objTest.doAnOperation())
    }


    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<Int>


    @Captor
    lateinit var argumentString: ArgumentCaptor<String>
    @Mock
    lateinit var mockTest: IntestCap

    @Test
    fun captureString() {

        mockTest.acarayEl16esmyCumple("27 años")
        Mockito.verify(mockTest).acarayEl16esmyCumple(argumentString.capture())
        assertEquals("27 años", argumentString.value)
    }

    @Test
    fun captorTest() {

        //mockList.add(1)
        //mockList.add("one")
        //Mockito.verify(mockList).add(arg.capture())

        //argumentCaptor = ArgumentCaptor.forClass(SpyTest::class.java)
        mockTest.doSomething(1)
        Mockito.verify(mockTest).doSomething(argumentCaptor.capture())
        assertEquals(1, argumentCaptor.value)

        //mySpyTest.operationArgs(1)
        //Mockito.verify(mySpyTest).operationArgs(argumentCaptor.capture())
        //assertEquals(2, argumentCaptor.value)

//        val argumentList: ArgumentCaptor<ArrayList<String>> = ArgumentCaptor.forClass(ArrayList<String>)
//        nameList.add("joel")
//        Mockito.verify(nameList).add()
//
//        val argument: ArgumentCaptor<SpyTest> = ArgumentCaptor.forClass(SpyTest::class.java)
//        Mockito.verify(myCapTest).operationArgs(2)
//       // verify(objTest).doSomething(argument.capture())
//        assertEquals(3, argument.value.operationArgs(2))

    }


    @Mock
    var mockedList: ArrayList<Int> = arrayListOf()

    @Captor
    lateinit var argCaptor: ArgumentCaptor<Int>

    @Test
    fun whenUseCaptorAnnotation_thenTheSam() {
        mockedList.add(1)
        Mockito.verify(mockedList).add(argCaptor.capture())
        assertEquals(1, argCaptor.value)
    }
}