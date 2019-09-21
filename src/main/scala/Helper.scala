import java.nio.file.{Files, Paths}

import com.lambdaworks.jacks.JacksMapper

import scala.util.Try

trait Helper {
  def getJson(storeName: String): Try[Map[String, String]] = Try(JacksMapper.readValue[Map[String, String]](Files.readAllLines(Paths.get(storeName)).get(0)))

  def writeJson(storeName: String, jsonMap: Map[String, String]): Try[Unit] = Try(Files.write(Paths.get(storeName), JacksMapper.writeValueAsString(jsonMap).getBytes()))
}
