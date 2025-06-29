package max.keils.binchecker.presentation.ui.screens.binchecker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import max.keils.domain.error.BinLookupError
import max.keils.domain.error.BinLookupException
import max.keils.domain.usecase.GetBinDetailsUseCase
import javax.inject.Inject

@HiltViewModel
class BinCheckerViewModel @Inject constructor(
    private val getBinDetailsUseCase: GetBinDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BinCheckerUiState>(BinCheckerUiState.Idle)
    val uiState: StateFlow<BinCheckerUiState>
        get() = _uiState.asStateFlow()

    private val _currentBin = MutableStateFlow("")
    val currentBin: StateFlow<String>
        get() = _currentBin.asStateFlow()

    fun onBinInputChange(newBin: String) {
        _currentBin.value = newBin.take(8)

        if (_uiState is BinCheckerUiState.Success || _uiState is BinCheckerUiState.Error)
            _uiState.value = BinCheckerUiState.Idle
    }

    fun binLookup() {
        val bin = _currentBin.value.trim()
        if (bin.isEmpty()) {
            _uiState.value = BinCheckerUiState.Error("Please enter a BIN number")
            return
        }

        _uiState.value = BinCheckerUiState.Loading

        viewModelScope.launch {
            val result = getBinDetailsUseCase(bin)
            result.fold(
                onSuccess = { binDetails ->
                    _uiState.value = BinCheckerUiState.Success(binDetails)
                },
                onFailure = { throwable ->
                    val errorMessage = when (throwable) {
                        is BinLookupException -> when (throwable.error) {
                            is BinLookupError.ApiError -> "API Error: ${throwable.message}"
                            BinLookupError.BinNotFound -> "BIN not found. No information available for this card."
                            BinLookupError.InvalidBinFormat -> "Invalid BIN format. Please enter 6-8 digits."
                            BinLookupError.NetworkError -> "Network error. Please check your internet connection."
                            is BinLookupError.UnknownError -> "An unknown error occurred: ${throwable.message}"
                        }

                        else -> "An unexpected error occurred: ${throwable.message}"
                    }
                    _uiState.value = BinCheckerUiState.Error(errorMessage)
                }
            )

        }
    }

}