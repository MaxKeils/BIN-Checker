package max.keils.binchecker.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.insert
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import max.keils.binchecker.R
import max.keils.binchecker.presentation.ui.theme.BINCheckerTheme
import max.keils.binchecker.presentation.ui.theme.Typography

@Composable
fun BankCard(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    bankName: String = "BANK NAME",
    cardHolder: String = "CARD HOLDER",
    cardScheme: String = "CARD SCHEME",
    cardType: String = "CARD TYPE",
    expireDate: String = "XX/XX"
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(0.4f.dp, color = MaterialTheme.colorScheme.onPrimaryContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = cardType,
                textAlign = TextAlign.Right,
                style = Typography.bodyLarge
            )

            Text(
                text = bankName,
                textAlign = TextAlign.Right,
                style = Typography.bodyLarge
            )
        }



        Spacer(modifier = Modifier.height(height = 8.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(size = 45.dp),
                painter = painterResource(R.drawable.ic_card_chip),
                contentDescription = stringResource(R.string.card_chip_icon)
            )
            Icon(
                painter = painterResource(R.drawable.ic_nfc),
                contentDescription = stringResource(R.string.nfc_icon),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(modifier = Modifier.height(height = 8.dp))

        BinInputField(state = textFieldState)

        Spacer(modifier = Modifier.height(height = 8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = expireDate,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Right
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = cardHolder,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Left
            )
            Text(
                text = cardScheme,
                style = MaterialTheme.typography.bodyLarge,
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BankCardPreview() {
    val state = rememberTextFieldState()
    BINCheckerTheme {
        BankCard(modifier = Modifier.padding(all = 16.dp), textFieldState = state)
    }
}

@Composable
private fun BinInputField(state: TextFieldState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicTextField(
            state = state,
            inputTransformation = InputTransformation {
                if (length > 8 || !asCharSequence().isDigitsOnly())
                    revertAllChanges()
            },
            outputTransformation = OutputTransformation {
                if (length > 4) insert(4, " ")
            },
            decorator = { innerTextField ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        if (state.text.isEmpty()) {
                            Text(
                                text = stringResource(R.string.input_bin),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                        innerTextField()
                    }
                }
            },
            lineLimits = TextFieldLineLimits.SingleLine
        )
        Spacer(modifier = Modifier.width(8.dp))

        repeat(3) {
            Text(
                text = "XXXX",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BinInputFieldPreview() {
    val state = rememberTextFieldState()
    BINCheckerTheme {
        BinInputField(state = state)
    }
}