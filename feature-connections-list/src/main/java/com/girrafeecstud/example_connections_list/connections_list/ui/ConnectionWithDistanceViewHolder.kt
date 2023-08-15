/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_list.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.core_base.base.cyrillicToLatin
import com.girrafeecstud.core_ui.R
import com.girrafeecstud.core_ui.extension.loadAndSetImage
import com.girrafeecstud.core_ui.extension.removeView
import com.girrafeecstud.core_ui.extension.showView
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.ConnectionWithDistance
import com.girrafeecstud.example_connections_list.connections_list.databinding.ConnectionWithDistanceItemBinding
import com.girrafeecstud.location_api.DistanceUnit
import com.girrafeecstud.location_api.getFormattedDistanceStringWithUnitFromDistanceInMeters
import java.util.Locale

class ConnectionWithDistanceViewHolder(
    private val binding: ConnectionWithDistanceItemBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        connectionWithDistance: ConnectionWithDistance,
        clickEvent: ConnectionClickEvent?
    ) {

        binding.connectionWithDistanceItem.setOnClickListener {
            clickEvent?.onConnectionClicked(connection = connectionWithDistance.connection)
        }

        binding.connectionProfileImage.loadAndSetImage(
            url = connectionWithDistance.connection.profileImageUrl
        )

        val connectionName = context.resources.getString(
            R.string.user_first_last_name,
            connectionWithDistance.connection.firstName,
            connectionWithDistance.connection.lastName
        )

        binding.connectionName.text = if (Locale.getDefault().language != "ru")
            connectionName.cyrillicToLatin()
        else
            connectionName

        if (connectionWithDistance.distance == null) {
            binding.distanceIcon.removeView()
            binding.connectionDistance.removeView()
        }

        connectionWithDistance.distance?.let { distance ->
            val formattedDistanceWithUnit = distance
                .getFormattedDistanceStringWithUnitFromDistanceInMeters()
            binding.connectionDistance.text = when (formattedDistanceWithUnit.second) {
                DistanceUnit.KILOMETERS -> context.resources.getString(
                    com.girrafeecstud.location_api.R.string.distance_in_km,
                    formattedDistanceWithUnit.first
                )
                DistanceUnit.METERS -> context.resources.getString(
                    com.girrafeecstud.location_api.R.string.distance_in_meters,
                    formattedDistanceWithUnit.first
                )
            }

            binding.distanceIcon.showView()
            binding.connectionDistance.showView()
        }

    }

}