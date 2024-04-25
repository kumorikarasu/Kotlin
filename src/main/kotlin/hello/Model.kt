package hello

import io.micronaut.serde.annotation.Serdeable
import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.Nullable
import com.fasterxml.jackson.annotation.JsonProperty

@Introspected
@Serdeable
class Data(
    @Nullable val id: Int,
    @JsonProperty("name") val name: String
)
