package com.fabio.plantmascot.ui

data class GardenUIState (
    val lux: Float = 0f,
    val humor: String = "Analisando...",
    val emoji: String = "🌱",
    val nivelPontos: Int = 0
)