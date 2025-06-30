package max.keils.binchecker.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import max.keils.binchecker.R
import max.keils.binchecker.presentation.ui.theme.BINCheckerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinCheckerScreenTopBar(
    onItemsListClick: () -> Unit = { }
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.app_name),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            )
        },
        actions = {
            IconButton(onClick = onItemsListClick) {
                Icon(Icons.AutoMirrored.Filled.List, contentDescription = "list")
            }
        }
    )
}

@Preview
@Composable
private fun TopBarPreview() {
    BINCheckerTheme {
        BinCheckerScreenTopBar()
    }
}