package org.mytemplatewizard.project.repository

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import org.mytemplatewizard.project.model.QueryResult


class SampleKtorRepositoryImpl(
    private val logger: LoggerRepository,
    private val client: HttpClient,
) : SampleKtorRepository {

    private val baseUrl = "https://api.github.com"

    override suspend fun searchGithubRepos(
        query: String,
        sort: String?,
        order: String?
    ): Result<QueryResult> {
        logger.d("Searching GitHub repositories with query: $query, sort: $sort, order: $order")
        return try {
            val queryResult: QueryResult = client.get("$baseUrl/search/repositories") {
                url {
                    parameters.append("q", query)
                    sort?.let { parameters.append("sort", it) }
                    order?.let { parameters.append("order", it) }
                }
                headers {
                    append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                }
            }.body()

            Result.success(queryResult)
        } catch (e: Exception) {
            Logger.e("Error executing device action: ${e.message}")
            Result.failure(e)
        }
    }
}