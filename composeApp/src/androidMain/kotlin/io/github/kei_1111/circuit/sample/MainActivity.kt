package io.github.kei_1111.circuit.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.zacsweers.metro.createGraphFactory
import io.github.kei_1111.circuit.sample.di.AndroidPlatformGraph
import io.github.kei_1111.circuit.sample.di.AppGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val platformGraph = createGraphFactory<AndroidPlatformGraph.Factory>()
            .create(applicationContext)
        val appGraph = createGraphFactory<AppGraph.Factory>()
            .create(platformGraph)

        setContent {
            App(appGraph)
        }
    }
}