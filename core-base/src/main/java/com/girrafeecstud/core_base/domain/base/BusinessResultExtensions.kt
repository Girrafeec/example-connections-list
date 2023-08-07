/* Created by Girrafeec */

package com.girrafeecstud.core_base.domain.base

import com.girrafeecstud.core_base.base.ExceptionType

fun <T> BusinessResult<T>.handleResult(
    onSuccess: (T?) -> Unit,
    onError: (BusinessError) -> Unit = {},
    onException: (ExceptionType) -> Unit = {}
) {
    when (this) {
        is BusinessResult.Success -> onSuccess(this.data)
        is BusinessResult.Error -> onError(this.businessError)
        is BusinessResult.Exception -> onException(this.exception)
    }
}
