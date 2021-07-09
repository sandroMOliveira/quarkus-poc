package org.poc

import org.poc.service.NasaService
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/rest/v1/nasa")
class ApiController @Inject constructor(private val nasaService: NasaService) {

    @GET
    @Path("/apod/{numbers_images}")
    @Produces(MediaType.APPLICATION_JSON)
    fun apodNasa(@PathParam("numbers_images") numberOfImages: Int) = nasaService.nasaApod(numberOfImages)
}