package com.wfg.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.wfg.todolist.ui.feature.addedit.AddEditScreen
import com.wfg.todolist.ui.feature.ListScreen
import kotlinx.serialization.Serializable


@Serializable
object ListRoute

@Serializable
data class AddEditRoute(val id: Long? = null)

@Composable
fun TodoNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ListRoute) {
        composable<ListRoute> {
            ListScreen(
                navigateToAddEditScreen = { id ->
                    navController.navigate(AddEditRoute(id))
                }
            )
        }

        composable<AddEditRoute> { route ->
            val addEditRout = route.toRoute<AddEditRoute>()
            AddEditScreen()
        }
    }
}