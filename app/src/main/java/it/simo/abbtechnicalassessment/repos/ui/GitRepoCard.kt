package it.simo.abbtechnicalassessment.repos.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import it.simo.abbtechnicalassessment.repos.model.GitRepo

@Composable
fun GitRepoCard(gitRepo: GitRepo, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            Column(
                modifier = modifier,
            ) {
                Text(gitRepo.name)
                Text(gitRepo.primaryLanguage)
                Text("⭐ ${gitRepo.starsNr}")
            }
        }
    }
}