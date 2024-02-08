package com.kenevisi.core.exceptions

import android.content.Context
import android.content.ContextWrapper
import java.util.Locale

fun Context.setAppLocale(language: AppLanguages): Context {
    val locale = Locale(language.locale)
    Locale.setDefault(locale)
    val config = resources.configuration.apply {
        setLocale(locale)
        setLayoutDirection(locale)
    }
    return ContextWrapper(createConfigurationContext(config))
}
