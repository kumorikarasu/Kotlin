package com.ryougi.hello

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Patch
import io.micronaut.http.annotation.Body
import io.micronaut.http.HttpStatus
import io.micronaut.serde.annotation.Serdeable
import java.util.Collections
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.inject.Inject

import com.ryougi.hello.HelloService
import com.ryougi.hello.Data

@Controller("/v1/hello")
class HelloController(val service: HelloService){
    companion object {
        @JvmField val LOG = LoggerFactory.getLogger(HelloController::class.java)
    }
    
    @Get("/")
    fun indexAll(): Map<String, List<Any>> {
        LOG.info("Get request for all")
        return Collections.singletonMap("hello", service.getAll())
    }

    @Get("/{id}")
    fun index(id: String): Map<String, Any?> {
        LOG.info("Get request for id: {}", id)

        return mapOf("hello" to service.getOne(id))
    }

    @Post("/")
    fun indexPost(@Body insert: Data): Map<String, Any> {
        LOG.info("Post request")
        return mapOf("hello" to service.insert(insert))
    }

    @Patch("/{id}")
    fun indexPatch(id: String, @Body insert: Data): Map<String, Any?> {
        LOG.info("Patch request for id: {}", id)

        return mapOf("hello" to service.update(id, insert))
    }

    @Delete("/{id}")
    fun indexDelete(id: String): Map<String, Any?> {
        LOG.info("Delete request for id: {}", id)

        return mapOf("hello" to service.delete(id))
    }
}
