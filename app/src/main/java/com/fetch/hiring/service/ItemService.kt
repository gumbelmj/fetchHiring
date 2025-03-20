package com.fetch.hiring.service

import com.fetch.hiring.model.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Single
import java.net.URL

class ItemService(private val gson: Gson = Gson()) {

    companion object {
        private const val END_POINT = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
    }

    fun retrieveData(): Single<ServiceResult> = Single.fromCallable { getData() }

    private fun getData(): ServiceResult {
        try {
            val jsonString = URL(END_POINT).readText()
            val result: Array<Item> = gson.fromJson(jsonString, typeToken)
            return ServiceResult.Success(result.toList())
        } catch (e: Exception) {
            return ServiceResult.Error(e)
        }
    }

    private val typeToken = object : TypeToken<Array<Item>>() {}.type
}