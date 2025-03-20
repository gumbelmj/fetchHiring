package com.fetch.hiring.ui

import com.fetch.hiring.logging.Logger
import com.fetch.hiring.model.Item
import com.fetch.hiring.model.ItemMapper
import com.fetch.hiring.service.ItemService
import com.fetch.hiring.service.ServiceResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainPresenter(
    private val view: MainFragment,
    private val itemService: ItemService = ItemService(),
    private val itemMapper: ItemMapper = ItemMapper(),
    private val logger: Logger = Logger(),
) {

    private var subscription = Disposable.disposed()

    fun onResume() {
        subscription.dispose()
        subscription = itemService
            .retrieveData()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { handleResult(it) },
                { onError(it) }
            )
    }

    fun onPause() {
        subscription.dispose()
    }

    private fun handleResult(serviceResult: ServiceResult) {
        when (serviceResult) {
            is ServiceResult.Success -> {
                view.updateModel(toModel(serviceResult))
            }

            is ServiceResult.Error -> {
                onError(serviceResult.exception)
            }
        }

    }

    private fun toModel(serviceResult: ServiceResult.Success): Map<Int, List<Item>> {
        return itemMapper.map(serviceResult.items)
    }

    private fun onError(throwable: Throwable) {
        logger.error("MainPresenter", "Error", throwable)
        view.displayError()
    }

}