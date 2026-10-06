package com.fabio.plantmascot.data

class LightSensorManager(context: Context) : SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val lightSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)[cite: 1]

    private var onLuxChangedListener: ((Float) -> Unit)? = null

    fun startListening(onLuxChanged: (Float) -> Unit) {
        onLuxChangedListener = onLuxChanged
        lightSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    fun stopListening() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val lux = event.values[0]
            onLuxChangedListener?.invoke(lux)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}