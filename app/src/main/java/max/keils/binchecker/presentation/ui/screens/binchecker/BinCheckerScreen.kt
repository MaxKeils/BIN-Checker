package max.keils.binchecker.presentation.ui.screens.binchecker

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import max.keils.binchecker.presentation.ui.components.BankCard
import max.keils.binchecker.presentation.ui.theme.BINCheckerTheme

@Composable
fun BinCheckerScreen(
    viewModel: BinCheckerViewModel = hiltViewModel()
) {

    val currentBin by viewModel.currentBin.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BankCard(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                currentBin = currentBin,
                onBinInputChange = viewModel::onBinInputChange
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { viewModel.binLookup() },
            ) {
                Text(text = "LOOKUP")
            }

            when (uiState) {
                BinCheckerUiState.Idle -> {
                    Text(text = "Enter BIN and click Lookup")
                }
                BinCheckerUiState.Loading -> {
                    CircularProgressIndicator()
                    Text(text = "Loading BIN details...")
                }
                is BinCheckerUiState.Success -> {
                    Log.d("UI_CHECKER", "${(uiState as BinCheckerUiState.Success).binDetails}")
                }
                is BinCheckerUiState.Error -> {
                    Log.d("UI_CHECKER", "error: ${(uiState as BinCheckerUiState.Error).message}")
                }
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