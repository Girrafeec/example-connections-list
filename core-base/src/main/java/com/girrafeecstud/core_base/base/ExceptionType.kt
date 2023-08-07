/* Created by Girrafeec */

package com.girrafeecstud.core_base.base

import com.girrafeecstud.core_base.R
import com.girrafeecstud.core_base.domain.base.CommonError

enum class ExceptionType(
    val exceptionTitle: String,
    val exceptionMessage: String
    ) {
    GPS_NOT_ENABLED(
        R.string.gps_not_enabled_exception_title.toString(),
        R.string.gps_not_enabled_exception_message.toString(),
    ),
    LOCATION_PERMISSIONS_NOT_GRANTED(
        R.string.location_permissions_not_granted_exception_title.toString(),
        R.string.location_permissions_not_granted_exception_message.toString(),
    )
}

fun ExceptionType.mapToCommonError(): CommonError {
    return CommonError(
        errorTitle = this.exceptionTitle,
        errorMessage = this.exceptionMessage
    )
}
