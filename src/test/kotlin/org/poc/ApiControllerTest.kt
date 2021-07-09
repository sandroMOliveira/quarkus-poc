package org.poc

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test

@QuarkusTest
class ApiControllerTest {

    @Test
    fun `get images of the day from nasa`() {
        given()
          .`when`().get("/rest/v1/nasa/apod/5")
          .then()
             .statusCode(200)
            .body(containsString("date"))
            .body(containsString("explanation"))
            .body(containsString("url"))
    }

}