package max.keils.binchecker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import max.keils.binchecker.presentation.ui.screens.binchecker.BinCheckerScreen
import max.keils.binchecker.presentation.ui.theme.BINCheckerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BINCheckerTheme {
                BinCheckerScreen()
            }
        }
    }
}