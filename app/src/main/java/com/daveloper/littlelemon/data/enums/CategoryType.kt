package com.daveloper.littlelemon.data.enums

import android.content.Context
import com.daveloper.littlelemon.R

enum class CategoryType {
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
        context.getString(R.string.lab_starters) -> CategoryType.STARTERS
        context.getString(R.string.lab_mains) -> CategoryType.MAINS
        context.getString(R.string.lab_desserts) -> CategoryType.DESSERTS
        context.getString(R.string.lab_sides) -> CategoryType.SIDES
        else -> CategoryType.UNKNOWN
    }
}

fun getAllCategoryTypeNames(
    context: Context
): List<String> = listOf(
    context.getString(R.string.lab_starters),
    context.getString(R.string.lab_mains),
    context.getString(R.string.lab_desserts),
    context.getString(R.string.lab_sides),
)