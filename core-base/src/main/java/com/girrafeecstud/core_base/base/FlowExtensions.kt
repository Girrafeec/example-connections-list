/* Created by Girrafeec */

package com.girrafeecstud.core_base.base

import com.girrafeecstud.core_base.domain.base.BusinessResult
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

//fun <T> Flow<BusinessResult<T>>.handleSuccessResultWithNotNullData(): Flow<T> =
//    flow {
//        collect { businessResult ->
//            when (businessResult) {
//                is BusinessResult.Success -> {
//                    businessResult.data?.let { emit(it) }
//                }
//                is BusinessResult.Error -> {
//                    emit(businessResult)
//                }
//                is BusinessResult.Exception -> {
//                    emit(businessResult)
//                }
//            }
//        }
//    }

fun <T> Flow<T>.unique(): Flow<T> =
    flow {
        var lastValue: Any? = NoValue
        collect { value: T ->
            if (lastValue != value) {
                lastValue = value
                emit(value)
            }
        }
    }

private object NoValue

fun CoroutineScope.launchPeriodically(
    repeatMillis: Long,
    isEnabled: () -> Boolean,
    action: () -> Unit
) : Job {
    return launch {
        while (isEnabled()) {
            action()
            delay(repeatMillis)
        }
    }
}

inline fun <T, R> Flow<BusinessResult<T>>.mapSuccess(
    crossinline transform: (T) -> R
): Flow<BusinessResult<R>> =
    map { result ->
        when (result) {
            is BusinessResult.Success -> {
                val transformedData = result.data?.let {
                    transform(it)
                }
                BusinessResult.Success(data = transformedData)
            }
            is BusinessResult.Error -> result
            is BusinessResult.Exception -> result
        }
    }