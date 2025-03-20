package com.fetch.hiring.ui

import com.fetch.hiring.extensions.RxJavaExtension
import com.fetch.hiring.logging.Logger
import com.fetch.hiring.model.Item
import com.fetch.hiring.model.ItemMapper
import com.fetch.hiring.service.ItemService
import com.fetch.hiring.service.ServiceResult
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class, RxJavaExtension::class)
class MainPresenterTest {

    @Mock
    private lateinit var view: MainFragment

    @Mock
    private lateinit var itemService: ItemService

    private lateinit var itemMapper: ItemMapper

    @Mock
    private lateinit var logger: Logger

    private lateinit var instance: MainPresenter

    @BeforeEach
    fun setUp() {

        itemMapper = ItemMapper()
        instance = MainPresenter(view, itemService, itemMapper, logger)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(view, itemService, logger)
    }

    @Test
    fun onResumeWithSuccess() {
        val item1 = Item(1, 1, "Item1")
        val item2 = Item(2, 1, "Item2")
        val result = ServiceResult.Success(listOf(item1, item2))
        `when`(itemService.retrieveData()).thenReturn(Single.just(result))

        instance.onResume()
        RxJavaExtension.testScheduler.triggerActions()

        verify(view).updateModel(mapOf(1 to listOf(item1, item2)))
    }

    @Test
    fun onResumeWithError() {
        val exception = RuntimeException()
        val result = ServiceResult.Error(exception)
        `when`(itemService.retrieveData()).thenReturn(Single.just(result))

        instance.onResume()
        RxJavaExtension.testScheduler.triggerActions()

        verify(view).displayError()
        verify(logger).error("MainPresenter", "Error", exception)
    }

    @Test
    fun onResumeWithException() {
        val result = ServiceResult.Success(listOf())
        `when`(itemService.retrieveData()).thenReturn(Single.just(result))

        val exception = RuntimeException()
        `when`(view.updateModel(emptyMap())).thenThrow(exception)

        instance.onResume()
        RxJavaExtension.testScheduler.triggerActions()

        verify(view).displayError()
        verify(logger).error("MainPresenter", "Error", exception)
    }

}