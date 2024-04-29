package com.ryougi.hello

import java.util.Collections
import jakarta.inject.Singleton
import jakarta.inject.Inject
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.discovery.event.ServiceReadyEvent
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.type.DataTypes
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder
import org.slf4j.LoggerFactory

import com.ryougi.hello.Data

@Singleton
class HelloOM {
  fun createTable(cqlSession: CqlSession) {
    cqlSession.execute(SchemaBuilder.createTable("hello")
      .ifNotExists()
      .withPartitionKey("id", DataTypes.UUID)
      .withColumn("name", DataTypes.TEXT)
      .build())
  }
}
