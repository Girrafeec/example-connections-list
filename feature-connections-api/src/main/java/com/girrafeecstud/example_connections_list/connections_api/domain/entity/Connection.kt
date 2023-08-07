package com.girrafeecstud.example_connections_list.connections_api.domain.entity

import android.os.Parcel
import android.os.Parcelable
import com.girrafeecstud.location_api.domain.Location
import kotlinx.parcelize.Parcelize

@Parcelize
data class Connection(
    val firstName: String,
    val lastName: String,
    val profileImageUrl: String,
    val location: Location
) : Parcelable
