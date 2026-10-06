package it.simo.abbtechnicalassessment.repos.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import it.simo.abbtechnicalassessment.ui.components.ErrorContent
import it.simo.abbtechnicalassessment.ui.components.LoadingContent

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

        else -> GitRepoList(state, onAction, modifier)
    }

}

@Composable
private fun GitRepoList(
    state: DashboardState,
    onAction: (DashboardAction) -> Unit,
    modifier: Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(state.gitRepositories.size) { index ->
            val gitRepo = state.gitRepositories[index]
            GitRepoCard(gitRepo, { onAction(DashboardAction.Click(gitRepo.owner, gitRepo.name)) })
        }
    }
}
