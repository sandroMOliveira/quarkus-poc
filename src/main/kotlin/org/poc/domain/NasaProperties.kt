package org.poc.domain

import io.smallrye.config.ConfigMapping

@ConfigMapping(prefix = "nasa-api")
class NasaProperties {

    var url: String = ""
    var token: String = ""
}