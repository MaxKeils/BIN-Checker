package max.keils.binchecker.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import max.keils.binchecker.presentation.ui.theme.BINCheckerTheme
import max.keils.binchecker.presentation.util.openMap
import max.keils.binchecker.presentation.util.openPhone
import max.keils.binchecker.presentation.util.openUrl
import max.keils.domain.entity.BankInfo
import max.keils.domain.entity.BinDetails
import max.keils.domain.entity.CountryInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetBinDetails(
    sheetState: SheetState,
    binDetails: BinDetails,
    onDismissRequest: () -> Unit = { }
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        BankCardSheetContent(
            binDetails = binDetails,
            onDismissRequest = onDismissRequest
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BankCardSheetContent(
    modifier: Modifier = Modifier,
    binDetails: BinDetails,
    onDismissRequest: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Information:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        BankCard(
            currentBin = binDetails.bin,
            bankName = binDetails.bank.name,
            cardScheme = binDetails.scheme,
            cardType = binDetails.type,
            expireDate = "XX/XX",
            isEditableBin = false
        )

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider(
            modifier = Modifier.clip(CircleShape),
            thickness = 2.dp
        )

        Spacer(modifier = Modifier.height(8.dp))

        RowItem("Brand", binDetails.brand.toString())
        RowItem("Country name", binDetails.country.name)
        RowItem("Country number", binDetails.country.numeric)
        RowItem(
            "Country latitude",
            binDetails.country.latitude.toString(),
            onClick = {
                openMap(
                    context,
                    binDetails.country.latitude ?: 0.0,
                    binDetails.country.longitude ?: 0.0
                )
            }
        )
        RowItem(
            "Country longitude",
            binDetails.country.longitude.toString(),
            onClick = {
                openMap(
                    context,
                    binDetails.country.latitude ?: 0.0,
                    binDetails.country.longitude ?: 0.0
                )
            }
        )
        RowItem(
            "Bank url",
            binDetails.bank.url,
            onClick = { openUrl(context, binDetails.bank.url) })
        RowItem(
            "Bank phone",
            binDetails.bank.phone,
            onClick = { openPhone(context, binDetails.bank.phone) }
        )
        RowItem("Bank city", binDetails.bank.city)


        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onDismissRequest
        ) {
            Text(text = "Close")
        }
    }
}

@Composable
private fun RowItem(label: String, value: String, onClick: (() -> Unit)? = null) {
    Column(
        modifier = Modifier.then(
            if (onClick != null) {
                Modifier.clickable(onClick = onClick)
            } else Modifier
        )
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = value,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.W400,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RowItemPreview() {
    BINCheckerTheme {
        RowItem("Country Name", "Russian Federation")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun ModalBottomSheetCardBankContentPreview() {
    BINCheckerTheme {
        val previewBinDetails = BinDetails(
            bin = "2222 4444",
            scheme = "visa",
            type = "debit",
            brand = "Visa Classic",
            prepaid = false,
            country = CountryInfo(
                numeric = "840",
                alpha2 = "US",
                name = "United States",
                emoji = "",
                currency = "USD",
                latitude = 38.0,
                longitude = -97.0
            ),
            bank = BankInfo(
                name = "Bank of America",
                url = "bankofamerica.com",
                phone = "1-800-432-1000",
                city = "Charlotte"
            )
        )

        BankCardSheetContent(
            binDetails = previewBinDetails,
            onDismissRequest = {}
        )
    }
}