package it.simo.abbtechnicalassessment.repodetails.ui

sealed interface GitRepoDetailsAction {
    data object Load : GitRepoDetailsAction
//    data object GoBack : GitRepoDetailsAction
}