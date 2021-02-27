package com.mario.babysnek

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mario.babysnek.entities.Snek

@Composable
fun SnekDetail(
    snek: Snek,
    onSnekAbandoned: (Snek) -> Unit,
    onSnekAdopted: (Snek) -> Unit){
    Column() {
        Image(
            painter = painterResource(id = snek.imageResource),
            contentDescription = snek.name,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth)
        Text(text = snek.name,
             style = MaterialTheme.typography.h2)
        Text(text = snek.description,
             style = MaterialTheme.typography.body1)
        Button (onClick = {
            if (snek.adopted){
                onSnekAbandoned(snek)
            }else {
                onSnekAdopted(snek)
            }
        }){
            if (snek.adopted){
                Text ("Don't abandon me :(")
            }else {
                Text ("Adopt me!")
            }
        }
    }
}

@Composable
@Preview
fun SnekDetailPreview (){
    MaterialTheme {
        Surface {
            SnekDetail(snek = generateSnekMap().toList().random().second, {}, {})
        }
    }
}