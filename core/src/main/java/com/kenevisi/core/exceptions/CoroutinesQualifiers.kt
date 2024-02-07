package com.kenevisi.core.exceptions

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher
