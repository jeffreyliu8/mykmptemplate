package org.mytemplatewizard.project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class QueryResult(
    @SerialName("total_count")
    val totalCount: Int,
    @SerialName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<QueryItem>? = null
)