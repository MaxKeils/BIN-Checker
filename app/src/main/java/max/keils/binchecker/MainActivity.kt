package max.keils.binchecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import max.keils.binchecker.ui.screens.BinCheckerScreen
import max.keils.binchecker.ui.theme.BINCheckerTheme

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