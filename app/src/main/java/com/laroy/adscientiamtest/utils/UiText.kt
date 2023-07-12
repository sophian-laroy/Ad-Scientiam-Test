package com.laroy.adscientiamtest.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    data class StringResource(@StringRes val id: Int) : UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> this.value
            is StringResource -> stringResource(id = this.id)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> this.value
            is StringResource -> context.getString(this.id)
        }
    }

}
