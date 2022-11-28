package com.gema.util

import com.gema.model.{EntityModel, EntityListModel}

import java.io.PrintWriter
import scala.util.{Failure, Success, Try}


object JsonWriter {

  def convertMembersToCsv(entityListModel: EntityListModel, outputPath: String,errorPath: String): Any = {
    val fieldMembersNames: Iterator[String] = entityListModel.entityModel.head.productElementNames
    val fieldValue: List[EntityModel] = entityListModel.entityModel
    Try(new PrintWriter(outputPath)) match {
      case Success(value) => {
        val header: String = fieldMembersNames.foldRight("")((prev, current) => prev + "," + current)
        value.println(header)
        Try(fieldValue.map(f => {
          val idValue: String = f.original_id match {
            case None => ","
            case Some(value) => value.toString + ","
          }
      }))

        value.close()
      }
    }
  }

  def logErrorToFile(path: String, errorMessage: String): Unit = {
    Try(new PrintWriter(path)) match {
      case Success(value) => value.println(errorMessage)
        value.close()
      case Failure(exception) => println(exception.getMessage)
    }
  }
}
