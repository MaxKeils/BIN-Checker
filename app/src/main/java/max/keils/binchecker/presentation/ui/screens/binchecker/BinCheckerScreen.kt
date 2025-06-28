package max.keils.binchecker.presentation.ui.screens.binchecker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import max.keils.binchecker.presentation.ui.components.BankCard
import max.keils.binchecker.presentation.ui.theme.BINCheckerTheme

@Composable
fun BinCheckerScreen(onCheckBinClick: () -> Unit = {}) {

    val binNumberState = rememberTextFieldState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BankCard(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                textFieldState = binNumberState
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onCheckBinClick,
            ) {
                Text(text = "LOOKUP")
            }
        }
    }
}

@Preview
@Composable
private fun BinCheckerScreenLightPreview() {
    BINCheckerTheme {
        BinCheckerScreen()
    }
}

@Preview
@Composable
private fun BinCheckerScreenDarkPreview() {
    BINCheckerTheme(darkTheme = true) {
        BinCheckerScreen()
    }
}