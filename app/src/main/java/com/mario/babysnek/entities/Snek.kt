package com.mario.babysnek.entities

data class Snek(val id: Int, val name: String, val description: String, val imageResource: Int, val adopted: Boolean = false)