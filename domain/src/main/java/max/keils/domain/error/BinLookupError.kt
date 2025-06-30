package max.keils.domain.error

sealed class BinLookupError() {

    object InvalidBinFormat : BinLookupError()

    object NetworkError : BinLookupError()

    object BinNotFound : BinLookupError()

    data class ApiError(val message: String) : BinLookupError()

    data class UnknownError(val message: String) : BinLookupError()

}

class BinLookupException(
    val error: BinLookupError
) :
    Exception()