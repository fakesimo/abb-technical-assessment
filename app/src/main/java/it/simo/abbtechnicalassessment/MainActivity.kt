package it.simo.abbtechnicalassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import it.simo.abbtechnicalassessment.repos.ui.DashboardEvent
import it.simo.abbtechnicalassessment.repos.ui.DashboardScreen
import it.simo.abbtechnicalassessment.repos.ui.DashboardViewModel
import it.simo.abbtechnicalassessment.ui.theme.ABBTechnicalAssessmentTheme
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ABBTechnicalAssessmentTheme {
                val viewModel: DashboardViewModel = koinViewModel()
                val state by viewModel.state.collectAsStateWithLifecycle()
                val snackbarHostState = remember { SnackbarHostState() }

                LaunchedEffect(Unit) {
                    viewModel.events.collect { event ->
                        when (event) {
                            is DashboardEvent.ShowMessage -> snackbarHostState.showSnackbar(event.text)
                        }
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { innerPadding ->
                    DashboardScreen(
                        state = state,
                        onAction = viewModel::onAction,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
