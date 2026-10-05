package it.simo.abbtechnicalassessment.di

import it.simo.abbtechnicalassessment.repodetails.ui.GitRepoDetailsViewModel
import it.simo.abbtechnicalassessment.repos.data.GitRepoRepository
import it.simo.abbtechnicalassessment.repos.data.RealGitRepoRepository
import it.simo.abbtechnicalassessment.repos.data.remote.GitHubApi
import it.simo.abbtechnicalassessment.repos.data.remote.createHttpClient
import it.simo.abbtechnicalassessment.repos.ui.DashboardViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { createHttpClient() }
    single { GitHubApi(get()) }

    single<GitRepoRepository> { RealGitRepoRepository(get()) }
    viewModel { DashboardViewModel("JetBrains", get()) }
    viewModel { (owner: String, name: String) -> GitRepoDetailsViewModel(owner, name, get()) }
}