package it.simo.abbtechnicalassessment.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import it.simo.abbtechnicalassessment.repodetails.ui.GitRepoDetailsScreen
import it.simo.abbtechnicalassessment.repodetails.ui.GitRepoDetailsViewModel
import it.simo.abbtechnicalassessment.repos.ui.DashboardEvent
import it.simo.abbtechnicalassessment.repos.ui.DashboardScreen
import it.simo.abbtechnicalassessment.repos.ui.DashboardViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Dashboard) {
        composable<Dashboard> {
            val viewModel: DashboardViewModel = koinViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val snackbarHostState = remember { SnackbarHostState() }

            LaunchedEffect(Unit) {
                viewModel.events.collect { event ->
                    when (event) {
                        is DashboardEvent.ShowMessage ->
                            snackbarHostState.showSnackbar(event.text)

                        is DashboardEvent.NavigateToDetails ->
                            navController.navigate(GitRepoDetails(event.name))
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
        composable<GitRepoDetails> { entry ->
            val route = entry.toRoute<GitRepoDetails>()
            val viewModel: GitRepoDetailsViewModel = koinViewModel { parametersOf(route.name) }
            val state by viewModel.state.collectAsStateWithLifecycle()

            Scaffold(
                modifier = Modifier.fillMaxSize(),
            ) { innerPadding ->
                GitRepoDetailsScreen(
                    state = state,
                    onAction = viewModel::onAction,
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}

