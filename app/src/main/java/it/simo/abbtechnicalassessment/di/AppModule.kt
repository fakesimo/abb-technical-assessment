package it.simo.abbtechnicalassessment.di

import it.simo.abbtechnicalassessment.BuildConfig
import it.simo.abbtechnicalassessment.assistant.data.AssistantRepository
import it.simo.abbtechnicalassessment.assistant.data.FakeAssistantRepository
import it.simo.abbtechnicalassessment.assistant.ui.AssistantViewModel
import it.simo.abbtechnicalassessment.data.createHttpClient
import it.simo.abbtechnicalassessment.repodetails.ui.GitRepoDetailsViewModel
import it.simo.abbtechnicalassessment.repos.data.GitRepoRepository
import it.simo.abbtechnicalassessment.repos.data.RealGitRepoRepository
import it.simo.abbtechnicalassessment.repos.data.remote.GitHubApi
import it.simo.abbtechnicalassessment.repos.ui.DashboardViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { createHttpClient() }
    single { GitHubApi(get(), BuildConfig.GITHUB_TOKEN) }

    single<GitRepoRepository> { RealGitRepoRepository(get()) }
    single<AssistantRepository> { FakeAssistantRepository() }
    viewModel { DashboardViewModel(get()) }
    viewModel { AssistantViewModel(get()) }
    viewModel { (owner: String, name: String) -> GitRepoDetailsViewModel(owner, name, get()) }
}