package max.keils.binchecker.presentation.ui.screens.binchecker

import max.keils.domain.entity.BinDetails

sealed class BinCheckerUiState {

    object Idle : BinCheckerUiState()

    object Loading : BinCheckerUiState()

    data class Success(val binDetails: BinDetails) : BinCheckerUiState()

    data class Error(val message: String) : BinCheckerUiState()

}