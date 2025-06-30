package max.keils.binchecker.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import max.keils.binchecker.presentation.ui.screens.binchecker.BinCheckerScreen
import max.keils.binchecker.presentation.ui.screens.history.BinHistoryScreen

@Serializable
object BinChecker

@Serializable
object History

@Composable
fun AppNavHost(modifier: Modifier = Modifier, navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = BinChecker,
        modifier = modifier
    ) {
        composable<BinChecker> {
            BinCheckerScreen(onOpenBinsListClick = {
                navHostController.navigate(route = History)
            })
        }
        composable<History> {
            BinHistoryScreen(onBackClick = navHostController::popBackStack)
        }
    }
}