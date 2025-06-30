package org.mytemplatewizard.project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryItem(
    @SerialName("full_name")
    val fullName: String,
    val description: String? = null,
    val owner: Owner,
    @SerialName("stargazers_count")
    val stargazersCount: Int
)