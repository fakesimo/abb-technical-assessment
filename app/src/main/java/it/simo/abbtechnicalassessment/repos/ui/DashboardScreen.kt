package it.simo.abbtechnicalassessment.repos.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import it.simo.abbtechnicalassessment.repos.model.Repo

@Composable
fun DashboardScreen(repos: List<Repo>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(repos.size) { index ->
            RepoCard(repos[index])
        }
    }
}