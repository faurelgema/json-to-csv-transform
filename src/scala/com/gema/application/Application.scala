package com.gema.application
import com.gema.config.Configuration
import com.gema.model.EntityListModel
import com.gema.util.{JsonReader, JsonWriter}

import scala.util.{Failure, Success, Try}

object Application extends App {
  val fileContent: Option[String] = Try(JsonReader.readFileToJson(Configuration.config.getString("inputpath"))) match {
    case Success(value) => Some(value)
    case Failure(exception) => Option.empty
  }
  if (fileContent.isEmpty) {
    JsonWriter
      .logErrorToFile(Configuration.config.getString("errorpath"), "Couldn't read the Json File")
  } else {
    val members: Try[EntityListModel] = Try(JsonReader.convertToMembers(fileContent.get))
    members match {
      case Success(value) =>
        JsonWriter.convertMembersToCsv(value, Configuration.config.getString("outputpath"),Configuration.config.getString("errorpath"))
      case Failure(exception) =>
        JsonWriter
          .logErrorToFile(Configuration.config.getString("errorpath"), exception.getMessage)

    }
  }


}