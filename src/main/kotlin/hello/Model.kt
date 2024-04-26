package com.ryougi.hello

import io.micronaut.serde.annotation.Serdeable
import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.Nullable
import com.fasterxml.jackson.annotation.JsonProperty
import com.datastax.oss.driver.api.mapper.annotations.Entity
import com.datastax.oss.driver.api.mapper.annotations.CqlName
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey
import com.datastax.oss.driver.api.core.uuid.Uuids
import java.util.UUID

@Introspected
@Serdeable
@Entity
@CqlName("hello")
class Data(
    @JsonProperty("id")
    @Nullable 
    @PartitionKey
    @CqlName("id")
    val id: UUID? = null,

    @JsonProperty("name") 
    @CqlName("name")
    val name: String
)
