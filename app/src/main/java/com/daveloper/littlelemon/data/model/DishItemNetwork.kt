package com.daveloper.littlelemon.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DishItemNetwork(
    @SerialName("id") val id: Long? = -1,
    @SerialName("title") val title: String? = "",
    @SerialName("description") val description: String? = "",
    @SerialName("price") val price: String? = "",
    @SerialName("image") val imageUrl: String? = "",
    @SerialName("category") val category: String? = "",
)