package it.simo.abbtechnicalassessment.repos.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen(
    state: DashboardState,
    onAction: (DashboardAction) -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        state.isLoading -> LoadingContent(modifier)

        state.error != null -> ErrorContent(
            state.error,
            { onAction(DashboardAction.Retry) },
            modifier,
        )

        else -> GitRepoList(state, modifier)
    }

}

@Composable
private fun LoadingContent(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
        )
    }
}

@Composable
private fun ErrorContent(errorMessage: String, onRetry: () -> Unit, modifier: Modifier) {
    Column(
        modifier = modifier,
    ) {
        Text(errorMessage)
        Button(
            onClick = { onRetry() },
        ) {
            Text("Retry")
        }
    }
}

@Composable
private fun GitRepoList(
    state: DashboardState,
    modifier: Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(state.gitRepositories.size) { index ->
            GitRepoCard(state.gitRepositories[index])
        }
    }
}
