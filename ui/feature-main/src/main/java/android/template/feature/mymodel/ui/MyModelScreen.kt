package android.template.feature.mymodel.ui

import android.template.core.ui.MyApplicationTheme
import android.template.feature.mymodel.ui.MyModelUiState.Success
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: MyModelViewModel = koinViewModel()) {
    val items by viewModel.uiState.collectAsStateWithLifecycle()
    var count: Int by remember { mutableStateOf(0) }
    var isButtonVisible by remember { mutableStateOf(true) }

    if (isButtonVisible) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Button(modifier = Modifier.fillMaxWidth(), onClick = { count++ }) {
                Text(text = "Count is: $count")
            }
        }
    }
    if (items is Success) {
        isButtonVisible = false
        MainScreen(
            productUiModel = (items as Success).data,
            onSave = viewModel::addMyModel,
            modifier = modifier,
        )
    }
}

@Composable
internal fun MainScreen(
    productUiModel: ProductUiModel,
    onSave: (name: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        var nameMyModel by remember { mutableStateOf("Compose") }
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TextField(
                value = nameMyModel,
                onValueChange = { nameMyModel = it },
            )

            Button(modifier = Modifier.width(96.dp), onClick = { onSave(nameMyModel) }) {
                Text("Save")
            }
        }
        Text("Saved item: ${productUiModel.name}")
    }
}

// Previews

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        MainScreen(ProductUiModel(name = "Product 1", price = null), onSave = {})
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitPreview() {
    MyApplicationTheme {
        MainScreen(ProductUiModel(name = "Product 1", price = null), onSave = {})
    }
}
