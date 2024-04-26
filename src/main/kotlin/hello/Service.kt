package com.ryougi.hello

import java.util.Collections
import jakarta.inject.Singleton
import jakarta.inject.Inject
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.type.DataTypes
import com.datastax.oss.driver.api.core.uuid.Uuids
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder

import org.slf4j.Logger;
import org.slf4j.LoggerFactory

import com.ryougi.hello.Data


@Singleton
class HelloService() {
  companion object {
      @JvmField val LOG = LoggerFactory.getLogger(HelloService::class.java)
  }

  @Inject lateinit var cqlSession: CqlSession

  fun getAll(): List<Data>{
    // Get data from cassandra
    val data = cqlSession.execute("SELECT * FROM hello").map { row ->
      Data(row.getUuid("id"), row.getString("name")!!)
    }.toList()
    return data;
  }

  fun getOne(id: Int): Data? {
    val data =
    cqlSession.execute("SELECT * FROM hello WHERE id = $id").map { row ->
      Data(row.getUuid("id"), row.getString("name")!!)
    }.firstOrNull()
    return data
  }

  fun insert(insert: Data): Data {
    val uuid = Uuids.random()
    cqlSession.execute("INSERT INTO hello (id, name) VALUES (${uuid}, '${insert.name}')");
    return Data(uuid, insert.name)
  }

  fun update(id: Int, insert: Data): Data? {

    LOG.info("UPDATE hello SET name = '${insert.name}' WHERE id=$id")
    cqlSession.execute("UPDATE hello SET name = '${insert.name}' WHERE id=$id")
    return getOne(id)
  }

  fun delete(id: Int): Data? {
    var data = getOne(id)
    cqlSession.execute("DELETE FROM hello WHERE id = ${id}")
    return data
  }
}
