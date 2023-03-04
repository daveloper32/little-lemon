package com.daveloper.littlelemon.data.enums

import android.content.Context
import com.daveloper.littlelemon.R

enum class CategoryType {
    ALL,
    STARTERS,
    MAINS,
    DESSERTS,
    SIDES,
    UNKNOWN
}

fun String.toCategoryType(
    context: Context
): CategoryType {
    return when(this) {
        context.getString(R.string.lab_all),
        context.getString(R.string.lab_all).lowercase() -> CategoryType.ALL
        context.getString(R.string.lab_starters),
        context.getString(R.string.lab_starters).lowercase() -> CategoryType.STARTERS
        context.getString(R.string.lab_mains),
        context.getString(R.string.lab_mains).lowercase() -> CategoryType.MAINS
        context.getString(R.string.lab_desserts),
        context.getString(R.string.lab_desserts).lowercase() -> CategoryType.DESSERTS
        context.getString(R.string.lab_sides),
        context.getString(R.string.lab_sides).lowercase() -> CategoryType.SIDES
        else -> CategoryType.UNKNOWN
    }
}

fun getAllCategoryTypeNames(
    context: Context
): List<String> = listOf(
    context.getString(R.string.lab_all),
    context.getString(R.string.lab_starters),
    context.getString(R.string.lab_mains),
    context.getString(R.string.lab_desserts),
    context.getString(R.string.lab_sides),
)