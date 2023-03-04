package com.daveloper.littlelemon.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuItemNetwork(
    @SerialName("id") val id: Long? = -1,
    @SerialName("title") val title: String? = "",
    @SerialName("description") val description: String? = "",
    @SerialName("price") val price: String? = "",
    @SerialName("image") val imageUrl: String? = "",
    @SerialName("category") val category: String? = "",
)

fun MenuItemNetwork.toMenuItemEntity() = MenuItemEntity(
    id = this.id?.toString()?.toIntOrNull() ?: -1,
    title = this.title ?: "",
    description = this.description ?: "",
    price = this.price?.toDoubleOrNull() ?: 0.0,
    imageUrl = this.imageUrl ?: "",
    category = this.category ?: ""
)