package it.simo.abbtechnicalassessment.di

import it.simo.abbtechnicalassessment.repodetails.ui.GitRepoDetailsViewModel
import it.simo.abbtechnicalassessment.repos.data.FakeGitRepoRepository
import it.simo.abbtechnicalassessment.repos.data.GitRepoRepository
import it.simo.abbtechnicalassessment.repos.ui.DashboardViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<GitRepoRepository> { FakeGitRepoRepository() }
    viewModel { DashboardViewModel(get()) }
    viewModel { (name: String) -> GitRepoDetailsViewModel(name, get()) }
}