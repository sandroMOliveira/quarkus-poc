package org.poc.client

import com.fasterxml.jackson.databind.ObjectMapper
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.poc.domain.NasaProperties
import org.poc.domain.NasaResponse
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers
import java.util.concurrent.CompletionStage
import java.util.concurrent.Executors
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.core.UriBuilder


@ApplicationScoped
@RestClient
class NasaClient @Inject constructor(properties: NasaProperties, private val mapper: ObjectMapper) {

    private val executorService = Executors.newCachedThreadPool()

    private val httpClient = HttpClient.newBuilder()
        .executor(executorService)
        .version(HttpClient.Version.HTTP_2)
        .build()

    private val baseUrl = properties.url
    private val token = properties.token

    fun findApod(numberOfMessages: Int): List<NasaResponse> {
        val uri = UriBuilder.fromPath("$baseUrl/planetary/apod")
            .queryParam("api_key", token).queryParam("count", numberOfMessages).build()
        val request = HttpRequest.newBuilder()
            .GET()
            .uri(uri)
            .header("Accept", "application/json")
            .build()
        val response = httpClient.sendAsync(request, BodyHandlers.ofString()).get()

        return mapper.readValue(response.body(), Array<NasaResponse>::class.java).toList()
    }
}