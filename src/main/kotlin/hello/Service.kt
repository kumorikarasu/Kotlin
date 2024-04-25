package hello

import java.util.Collections
import jakarta.inject.Singleton
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

import hello.Data

@Singleton
class HelloService {
  val data = mutableListOf<Data>()

  fun getAll(): List<Data>{
    return data;
  }

  fun getOne(id: Int): Data? {
    return data.find { it.id == id }
  }

  fun insert(insert: Data): Data {
    data.add(Data(data.size + 1, insert.name))
    return data.last()
  }

  fun update(id: Int, insert: Data): Data? {
    data.find { it.id == id }?.let {
      data.set(data.indexOf(it), Data(it.id, insert.name))
    }
    return getOne(id)
  }

  fun delete(id: Int): Data? {
    val obj = getOne(id)
    data.removeIf { it.id == id }
    return obj
  }
}
