package com.gema.util

import com.gema.model.{EntityModel, EntityListModel}
import spray.json.DefaultJsonProtocol._
import spray.json._
import scala.io.Source
import scala.util.{Failure, Success, Try}

object JsonReader {

  def readFileToJson(path: String): String = {
    Try(Source.fromFile(path)) match {
      case Success(source: Source) =>
        source.getLines().mkString
      case Failure(exception) =>
         "Couldn't read the message" + exception.getMessage
    }
  }
  implicit val entityModelJsonFormat: RootJsonFormat[EntityModel] =
    jsonFormat6(EntityModel)

  implicit val entityListModelJsonFormat: RootJsonFormat[EntityListModel] = jsonFormat1(EntityListModel)


  def convertToMember(entityModel : String): EntityModel = {
    entityModel.parseJson.convertTo[EntityModel]
  }

  def convertToMembers(entityListModel: String ): EntityListModel = {
    entityListModel.parseJson.convertTo[EntityListModel]
  }


}