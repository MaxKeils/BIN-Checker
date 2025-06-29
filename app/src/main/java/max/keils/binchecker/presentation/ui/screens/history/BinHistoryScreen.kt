package max.keils.binchecker.presentation.ui.screens.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import max.keils.binchecker.presentation.ui.components.BinHistoryItem
import max.keils.binchecker.presentation.ui.components.ModalBottomSheetBinDetails
import max.keils.binchecker.presentation.ui.theme.BINCheckerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinHistoryScreen(
    viewModel: BinHistoryViewModel = hiltViewModel<BinHistoryViewModel>()
) {

    val binList by viewModel.binList.collectAsState()
    val selectedBinDetails by viewModel.selectedBin.collectAsState()

    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(selectedBinDetails) {
        if (selectedBinDetails != null)
            sheetState.show()
        else
            sheetState.hide()
    }

    Scaffold { innerPadding ->

        if (binList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(modifier = Modifier.padding(all = 16.dp), text = "No BINs saves yet.")
            }
        } else {
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(binList) { binDetails ->
                    BinHistoryItem(bin = binDetails.bin, onDetailsButtonClick = {
                        viewModel.setSelectedBinDetails(binDetails)
                    })
                }
            }
        }

        selectedBinDetails?.let {
            ModalBottomSheetBinDetails(
                sheetState = sheetState,
                binDetails = it,
                onDismissRequest = {
                    viewModel.setSelectedBinDetails(null)
                }
            )
        }

    }
}


@Preview
@Composable
private fun BinListScreenLightPreview() {
    BINCheckerTheme(darkTheme = false) {
        BinHistoryScreen()
    }
}

@Preview
@Composable
private fun BinListScreenDarkPreview() {
    BINCheckerTheme(darkTheme = true) {
        BinHistoryScreen()
    }
}