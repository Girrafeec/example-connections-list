/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_impl.domain.usecase

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.location_api.domain.Location
import com.girrafeecstud.location_tracker_api.domain.IGetLastKnownLocationUseCase
import com.girrafeecstud.location_tracker_impl.domain.repository.ILocationTrackerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetLastKnownLocationUseCase @Inject constructor(
    private val repository: ILocationTrackerRepository
): IGetLastKnownLocationUseCase {

    override operator fun invoke(): Flow<BusinessResult<Location>> =
        // TODO create app dispatchers and make di with it
        repository.getLastKnownLocation().flowOn(Dispatchers.IO)

}