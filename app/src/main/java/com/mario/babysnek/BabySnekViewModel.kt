package com.mario.babysnek

import androidx.collection.ArrayMap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mario.babysnek.entities.Snek

class BabySnekViewModel: ViewModel() {

    var selectedSnek: Snek? by mutableStateOf(null)
        private set
    var snekMap: ArrayMap<Int, Snek> by mutableStateOf(generateSnekMap())

    fun onSnekSelected (snek: Snek){
        selectedSnek = snek
    }

    fun unselectSnake (){
        selectedSnek = null
    }

    fun adoptSnek (snek: Snek){
        snekMap = snekMap.apply {
            snekMap[snek.id] = snek.copy(adopted = true)
        }
        selectedSnek = selectedSnek?.copy(adopted = true)

    }

    fun abandonSnek (snek: Snek){
        snekMap = snekMap.apply {
            snekMap[snek.id] = snek.copy(adopted = false)
            selectedSnek = selectedSnek?.copy(adopted = false)
        }
    }
}

fun generateSnekMap(): ArrayMap<Int, Snek>{
    val snekMap = ArrayMap<Int, Snek>()

    val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

    snekMap.put(0, Snek(0, "Danger Spaguetti", loremIpsum, R.drawable.snake1))
    snekMap.put(1, Snek(1, "Cuddles", loremIpsum, R.drawable.snake2))
    snekMap.put(2, Snek(2, "Monty the Python", loremIpsum, R.drawable.snake3))
    snekMap.put(3, Snek(3, "Cupcake", loremIpsum, R.drawable.snake4))
    snekMap.put(4, Snek(4, "Buttercup", loremIpsum, R.drawable.snake5))
    snekMap.put(5, Snek(5, "Severus", loremIpsum, R.drawable.snake6))
    snekMap.put(6, Snek(6, "Noodles", loremIpsum, R.drawable.snake7))
    snekMap.put(7, Snek(7, "Dolores", loremIpsum, R.drawable.snake8))

    return snekMap
}