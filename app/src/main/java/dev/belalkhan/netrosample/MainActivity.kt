package dev.belalkhan.netrosample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import androidx.navigation.compose.rememberNavController
//import dagger.hilt.android.AndroidEntryPoint
//import dev.belalkhan.netrosample.navigation.NavGraph
import dev.belalkhan.netrosample.ui.theme.NetroSampleTheme

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetroSampleTheme {
                Text(
                    text = "Hello Android! YOO",
                    modifier = Modifier.padding(16.dp)
                )
//                val navController = rememberNavController()
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    NavGraph(
//                        navController = navController,
//                        modifier = Modifier.padding(innerPadding),
//                    )
//                }
            }
        }
    }
}
