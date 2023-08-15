/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_impl.domain.usecase

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.core_components_api.DispatcherProvider
import com.girrafeecstud.location_api.domain.Location
import com.girrafeecstud.location_tracker_api.domain.IGetLastKnownLocationUseCase
import com.girrafeecstud.location_tracker_impl.domain.repository.ILocationTrackerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetLastKnownLocationUseCase @Inject constructor(
    private val repository: ILocationTrackerRepository,
    private val dispatchers: DispatcherProvider
): IGetLastKnownLocationUseCase {

    override operator fun invoke(): Flow<BusinessResult<Location>> =
        repository.getLastKnownLocation().flowOn(dispatchers.io)

}