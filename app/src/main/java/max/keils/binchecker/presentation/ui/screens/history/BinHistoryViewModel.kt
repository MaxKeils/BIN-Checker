package max.keils.binchecker.presentation.ui.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import max.keils.domain.entity.BinDetails
import max.keils.domain.usecase.GetAllDetailsUseCase
import javax.inject.Inject

@HiltViewModel
class BinHistoryViewModel @Inject constructor(
    private val getAllDetailsUseCase: GetAllDetailsUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            getAllDetailsUseCase().collect {
                _binList.value = it
            }
        }
    }

    private val _binList = MutableStateFlow<List<BinDetails>>(emptyList())
    val binList: StateFlow<List<BinDetails>>
        get() = _binList.asStateFlow()

    private val _selectedBin = MutableStateFlow<BinDetails?>(null)
    val selectedBin: StateFlow<BinDetails?>
        get() = _selectedBin.asStateFlow()

    fun setSelectedBinDetails(binDetails: BinDetails?) {
        _selectedBin.value = binDetails
    }

}