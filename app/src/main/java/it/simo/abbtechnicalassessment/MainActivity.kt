package it.simo.abbtechnicalassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import it.simo.abbtechnicalassessment.repos.model.Repo
import it.simo.abbtechnicalassessment.repos.ui.DashboardScreen
import it.simo.abbtechnicalassessment.ui.theme.ABBTechnicalAssessmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ABBTechnicalAssessmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DashboardScreen(
                        repos = generateSequence('a') { it + 1 }
                            .map { Repo(name = it.toString(), language = "Kotlin") }
                            .take(100)
                            .toList(),
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
