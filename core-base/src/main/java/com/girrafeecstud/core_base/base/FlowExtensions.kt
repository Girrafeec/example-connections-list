/* Created by Girrafeec */

package com.girrafeecstud.core_base.base

import com.girrafeecstud.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow

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