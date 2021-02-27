package com.mario.babysnek

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.mario.babysnek.ui.theme.BabySnekTheme

class MainActivity: AppCompatActivity() {

    private val viewModel by viewModels<BabySnekViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BabySnekTheme { // A surface container using the 'background' color from the theme
                Surface {
                    BabySnekContent(viewModel)
                }
            }
        }
    }

    override fun onBackPressed() {
        if (viewModel.selectedSnek != null) {
            viewModel.unselectSnake()
        } else {
            super.onBackPressed()
        }
    }
}

@Composable
fun BabySnekContent(viewModel: BabySnekViewModel) {
    val isDetail = viewModel.selectedSnek == null
    Scaffold(topBar = { AppBar(viewModel) }) {
        if (isDetail) {
            SnekList(sneks = viewModel.snekMap.toList().map { it.second }, onSnekSelected = viewModel::onSnekSelected)
        } else {
            SnekDetail(snek = viewModel.selectedSnek!!,
                       onSnekAbandoned = viewModel::abandonSnek,
                       onSnekAdopted = viewModel::adoptSnek)
        }
    }
}

@Composable
fun AppBar(viewModel: BabySnekViewModel) {
    if (viewModel.selectedSnek == null) {
        MainScreenAppBar()
    } else {
        DetailScreenAppBar(viewModel = viewModel)
    }
}

@Composable
fun MainScreenAppBar() {
    TopAppBar(title = { Text("Adopt a snek!") })
}

@Composable
fun DetailScreenAppBar(viewModel: BabySnekViewModel) {
    TopAppBar(title = { Text("Adopt a snek!") }, navigationIcon = {
        IconButton(onClick = { viewModel.unselectSnake() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
        }
    })
}

