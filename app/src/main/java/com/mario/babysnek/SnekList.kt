package com.mario.babysnek

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mario.babysnek.entities.Snek
import com.mario.babysnek.ui.theme.BabySnekTheme

@Composable
fun SnekList(
    sneks: List<Snek>,
    onSnekSelected: (Snek) -> Unit){
    LazyColumn{
        items(items = sneks) { snek ->
            SnekListItem(
                snek = snek,
                onSnekSelected = onSnekSelected
                        )
        }
    }
}

@Composable
fun SnekListItem(
    snek: Snek,
    onSnekSelected: (Snek) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onSnekSelected(snek)
        }
       ) {
        Image(painter = painterResource(id = snek.imageResource),
              contentDescription = snek.name,
              contentScale = ContentScale.FillHeight,
              modifier = Modifier
                      .size(100.dp, 100.dp)
                      .padding(8.dp)
                      .clip(CircleShape)
             )
        Column ( modifier = Modifier.weight(1f)
               ) {
            Text(
                snek.name,
                style = MaterialTheme.typography.h5
                )
            Text(
                snek.description,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
        }
        val iconModifier = Modifier.padding(8.dp).size(24.dp, 24.dp)
        if (snek.adopted) {
            Image(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Adopted",
                modifier = iconModifier)
        } else {
            Image(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Not adopted",
                modifier = iconModifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SnekItemPreview() {
    BabySnekTheme {
        Surface {
            SnekListItem(snek = generateSnekMap().toList().random().second) {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SnekListPreview() {
    BabySnekTheme {
        Surface {
            SnekList(generateSnekMap().toList().map { it.second }) {}
        }
    }
}