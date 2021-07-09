package org.poc.service

import org.eclipse.microprofile.rest.client.inject.RestClient
import org.kxtra.slf4j.getLogger
import org.poc.client.NasaClient
import org.poc.domain.NasaResponse
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.GET

@ApplicationScoped
class NasaService @Inject constructor(@RestClient private val nasaClient: NasaClient) {

    companion object {
        private val LOGGER = getLogger()
    }

    @GET
    fun nasaApod(numberOfImage: Int): List<NasaResponse> {
        LOGGER.info("get Astronomy Picture Day")
        return nasaClient.findApod(numberOfImage)
    }
}