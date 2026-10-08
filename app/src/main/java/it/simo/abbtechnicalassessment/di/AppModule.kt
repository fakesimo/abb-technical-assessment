package it.simo.abbtechnicalassessment.di

import it.simo.abbtechnicalassessment.BuildConfig
import it.simo.abbtechnicalassessment.assistant.data.AssistantRepository
import it.simo.abbtechnicalassessment.assistant.data.RealAssistantRepository
import it.simo.abbtechnicalassessment.assistant.data.remote.GeminiApi
import it.simo.abbtechnicalassessment.assistant.domain.AssistantTool
import it.simo.abbtechnicalassessment.assistant.domain.ListReposTool
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
    single { GeminiApi(get(), BuildConfig.GEMINI_KEY) }

    single<GitRepoRepository> { RealGitRepoRepository(get()) }
    viewModel { DashboardViewModel(get()) }
    viewModel { (owner: String, name: String) -> GitRepoDetailsViewModel(owner, name, get()) }

    single<AssistantTool> { ListReposTool(get()) }
    single<AssistantRepository> { RealAssistantRepository(get(), get()) }
    viewModel { AssistantViewModel(get()) }
}