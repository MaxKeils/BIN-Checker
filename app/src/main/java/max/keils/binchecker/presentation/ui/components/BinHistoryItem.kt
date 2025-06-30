package max.keils.binchecker.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import max.keils.binchecker.presentation.ui.theme.BINCheckerTheme

@Composable
fun BinHistoryItem(
    modifier: Modifier = Modifier,
    bin: String,
    onDetailsButtonClick: (bin: String) -> Unit = { },
) {
    Card(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val formattedBin = bin.format().let {
                it.substring(0, 4) + " " + it.substring(4)
            }
            Text(
                modifier = Modifier.padding(all = 8.dp),
                text = "BIN: $formattedBin",
                fontWeight = FontWeight.Bold
            )
            TextButton(
                onClick = {
                    onDetailsButtonClick(bin)
                }
            ) {
                Text(text = "Details")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BinHistoryItemPreview() {
    BINCheckerTheme {
        BinHistoryItem(modifier = Modifier.padding(all = 16.dp), bin = "12333333")
    }
}