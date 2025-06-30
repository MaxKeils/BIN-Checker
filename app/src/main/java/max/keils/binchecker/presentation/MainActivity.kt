package max.keils.binchecker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import max.keils.binchecker.presentation.ui.navigation.AppNavHost
import max.keils.binchecker.presentation.ui.screens.binchecker.BinCheckerScreen
import max.keils.binchecker.presentation.ui.theme.BINCheckerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BINCheckerTheme {
                Surface {
                    val navController = rememberNavController()
                    AppNavHost(
                        navHostController = navController
                    )
                }
            }
        }
    }
}