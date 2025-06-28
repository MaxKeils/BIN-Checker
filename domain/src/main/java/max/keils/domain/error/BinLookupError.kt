package max.keils.domain.error

sealed class BinLookupError() {

    object InvalidBinFormat : BinLookupError()

}

class BinLookupException(val error: BinLookupError) : Exception()