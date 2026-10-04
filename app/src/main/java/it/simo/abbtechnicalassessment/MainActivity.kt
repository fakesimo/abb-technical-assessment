package it.simo.abbtechnicalassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import it.simo.abbtechnicalassessment.navigation.AppNavHost
import it.simo.abbtechnicalassessment.ui.theme.ABBTechnicalAssessmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ABBTechnicalAssessmentTheme {
                AppNavHost()
            }
        }
    }
}
