package it.simo.abbtechnicalassessment.repodetails.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GitRepoDetailsScreen(
    state: GitRepoDetailsState,
    onAction: (GitRepoDetailsAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    when {
        state.isLoading -> LoadingContent(modifier)

        state.error != null -> ErrorContent(
            state.error,
            { onAction(GitRepoDetailsAction.Load) },
            modifier,
        )

        else -> Column(
            modifier = modifier,
        ) {
            Text("${state.gitRepo?.name}")
            Text("${state.gitRepo?.description}")
            Text("${state.gitRepo?.starsNr} ⭐")
        }
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