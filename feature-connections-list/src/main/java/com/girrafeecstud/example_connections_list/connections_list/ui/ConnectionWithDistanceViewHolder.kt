/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_list.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.core_ui.R
import com.girrafeecstud.core_ui.extension.loadAndSetImage
import com.girrafeecstud.core_ui.extension.removeView
import com.girrafeecstud.core_ui.extension.showView
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.ConnectionWithDistance
import com.girrafeecstud.example_connections_list.connections_list.databinding.ConnectionWithDistanceItemBinding
import com.girrafeecstud.location_api.data.getFormattedDistanceStringTest

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
            connectionWithDistance.connection.profileImageUrl
        )

        binding.connectionName.text = context.resources.getString(
            R.string.user_first_last_name,
            connectionWithDistance.connection.firstName,
            connectionWithDistance.connection.lastName
        )

        if (connectionWithDistance.distance == null) {
            binding.distanceIcon.removeView()
            binding.connectionDistance.removeView()
        }

        connectionWithDistance.distance?.let { distance ->
            binding.connectionDistance.text = distance.getFormattedDistanceStringTest()
            binding.distanceIcon.showView()
            binding.connectionDistance.showView()
        }

    }

}