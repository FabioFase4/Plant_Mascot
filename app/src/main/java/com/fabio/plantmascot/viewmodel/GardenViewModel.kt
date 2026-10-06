package com.fabio.plantmascot.viewmodel

class GardenViewModel(application: Application) : AndroidViewModel(application) {

    private val sensorManager = LightSensorManager(application)

    // Estado exposto para a View (read-only)
    var uiState by mutableStateOf(GardenUiState())
        private set

    fun iniciarMonitoramento() {
        sensorManager.startListening { lux ->
            atualizarEstadoComLux(lux)
        }
    }

    fun pararMonitoramento() {
        sensorManager.stopListening()
    }

    private fun atualizarEstadoComLux(lux: Float) {
        val (humor, emoji) = when {
            lux < 100f -> "Com sono (Escuro)" to "😴"
            lux in 100f..1000f -> "Feliz (Luz Ideal)" to "🌻"
            else -> "Com calor (Luz Forte)" to "☀️"
        }

        uiState = uiState.copy(
            lux = lux,
            humor = humor,
            emoji = emoji
        )
    }

    fun cuidarDaPlanta() {
        uiState = uiState.copy(
            nivelPontos = uiState.nivelPontos + 10
        )
    }
}