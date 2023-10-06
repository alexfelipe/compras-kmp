package br.com.alura.compras.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import br.com.alura.compras.android.ui.screens.ShoppingListScreen
import br.com.alura.compras.android.ui.uistate.ProductListUiState
import br.com.alura.compras.android.ui.viewmodels.ShoppingListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel by viewModels<ShoppingListViewModel>()
                    val uiState by viewModel.uiState.collectAsState(ProductListUiState())
                    ShoppingListScreen(
                        uiState = uiState,
                        onSaveProduct = { name ->
                            viewModel.save(name)
                        }
                    )
                }
            }
        }
    }
}