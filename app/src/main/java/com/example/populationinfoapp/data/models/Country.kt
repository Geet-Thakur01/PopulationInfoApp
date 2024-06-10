package com.example.populationinfoapp.data.models

data class Country(
    val abbreviation: String,
    val capital: String,
    val currency: String,
    val id: Int,
    val media: Media,
    val name: String,
    val phone: String,
    val population: Int
)