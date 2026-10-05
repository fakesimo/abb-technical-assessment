package it.simo.abbtechnicalassessment.repodetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import it.simo.abbtechnicalassessment.ui.components.ErrorContent
import it.simo.abbtechnicalassessment.ui.components.LoadingContent

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
