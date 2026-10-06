package com.fabio.plantmascot.ui

@Composable
fun GardenScreen(
    viewModel: GardenViewModel = viewModel()
) {
    val state = viewModel.uiState

    // Controla o ciclo de vida da escuta do sensor quando a tela abre/fecha
    DisposableEffect(Unit) {
        viewModel.iniciarMonitoramento()
        onDispose {
            viewModel.pararMonitoramento()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Meu Jardim Virtual", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Luminosidade: ${state.lux.toInt()} lux")[cite: 1]
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = state.emoji, fontSize = 72.sp)
        Text(text = state.humor, style = MaterialTheme.typography.bodyLarge)
        Text(text = "Pontos de Cuidado: ${state.nivelPontos}")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { viewModel.cuidarDaPlanta() }) {
            Text("Cuidar")
        }
    }
}