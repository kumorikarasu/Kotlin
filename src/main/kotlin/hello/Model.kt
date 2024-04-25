

import io.micronaut.serde.annotation.Serdeable
import io.micronaut.core.annotation.Introspected
import com.fasterxml.jackson.annotation.JsonProperty

@Introspected
@Serdeable
class Data(
    val id: Int,
    @JsonProperty("name") val name: String
)
