package org.mytemplatewizard.project.repository

import org.mytemplatewizard.project.model.QueryResult

interface SampleKtorRepository {
    suspend fun searchGithubRepos(
        query: String,
        sort: String? = null,
        order: String? = null,
    ): Result<QueryResult>
}
