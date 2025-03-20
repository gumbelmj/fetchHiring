package com.fetch.hiring.service

import com.fetch.hiring.model.Item

sealed class ServiceResult {

    data class Success(val items: List<Item>) : ServiceResult()

    data class Error(val exception: Exception) : ServiceResult()
}