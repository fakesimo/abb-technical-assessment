package it.simo.abbtechnicalassessment.navigation

import kotlinx.serialization.Serializable

@Serializable
object Dashboard

@Serializable
data class GitRepoDetails(val name: String)