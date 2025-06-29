package max.keils.binchecker.presentation.ui.screens.binchecker

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import max.keils.binchecker.presentation.ui.components.BankCard
import max.keils.binchecker.presentation.ui.components.BinCheckerScreenTopBar
import max.keils.binchecker.presentation.ui.components.ModalBottomSheetBinDetails
import max.keils.binchecker.presentation.ui.theme.BINCheckerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinCheckerScreen(
    viewModel: BinCheckerViewModel = hiltViewModel(),
    onItemsListClick: () -> Unit = { }
) {

    val currentBin by viewModel.currentBin.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    val sheetState = rememberModalBottomSheetState()

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState) {
        when (uiState) {
            BinCheckerUiState.Idle -> {
                if (sheetState.isVisible)
                    sheetState.hide()
            }

            BinCheckerUiState.Loading -> {}
            is BinCheckerUiState.Success -> {
                sheetState.show()
            }

            is BinCheckerUiState.Error -> {
                snackBarHostState.showSnackbar(message = (uiState as BinCheckerUiState.Error).message)
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { BinCheckerScreenTopBar(onItemsListClick = onItemsListClick) },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BankCard(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                currentBin = currentBin,
                onBinInputChange = viewModel::onBinInputChange,
                isEditableBin = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = viewModel::binLookup,
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

        if (sheetState.isVisible && uiState is BinCheckerUiState.Success) {
            ModalBottomSheetBinDetails(
                sheetState = sheetState,
                binDetails = (uiState as BinCheckerUiState.Success).binDetails,
                onDismissRequest = viewModel::resetToDefaultState,
            )
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