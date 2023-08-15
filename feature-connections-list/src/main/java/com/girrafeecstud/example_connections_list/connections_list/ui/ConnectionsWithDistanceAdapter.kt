/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_list.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.ConnectionWithDistance
import com.girrafeecstud.example_connections_list.connections_list.databinding.ConnectionWithDistanceItemBinding
import javax.inject.Inject

class ConnectionsWithDistanceAdapter @Inject constructor(

) : RecyclerView.Adapter<ConnectionWithDistanceViewHolder>() {

    var clickEvent: ConnectionClickEvent? = null

    private var connectionsWithDistance: ArrayList<ConnectionWithDistance> =
        ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConnectionWithDistanceViewHolder {
        val binding = ConnectionWithDistanceItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ConnectionWithDistanceViewHolder(binding = binding, context =  parent.context)
    }

    override fun onBindViewHolder(holder: ConnectionWithDistanceViewHolder, position: Int) {
        holder.bind(connectionsWithDistance[position], clickEvent = clickEvent)
    }

    override fun getItemCount(): Int =
        connectionsWithDistance.size

    fun updateConnections(connectionsWithDistance: List<ConnectionWithDistance>) {
        this.connectionsWithDistance = ArrayList(connectionsWithDistance)
        notifyDataSetChanged()
    }

    fun removePinnedConnection(connectionId: String) {
        val indexToRemove = connectionsWithDistance.indexOfFirst {
            it.connection.connectionId == connectionId
        }
        if (indexToRemove != -1) {
            connectionsWithDistance.removeAt(indexToRemove)
            notifyItemRemoved(indexToRemove)
        }
    }
}