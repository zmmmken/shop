package com.kenevisi.feature_core.extensions

import androidx.navigation.NavController


fun NavController.canNavigateUp(): Boolean {
    return previousBackStackEntry != null
}