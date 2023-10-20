package com.example.bitcointickercompose.utils

import android.content.Context
import androidx.annotation.StringRes
import com.example.bitcointickercompose.domain.utils.StringResourceProvider
import javax.inject.Inject

class StringResourceProviderImpl @Inject constructor(
    private val context: Context
) : StringResourceProvider {

    override fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }
}