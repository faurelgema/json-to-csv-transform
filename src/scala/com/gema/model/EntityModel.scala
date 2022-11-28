package com.gema.model

import java.time.ZonedDateTime

case class EntityModel(original_id:Option[Long], content:String, from_id:Int, from_name:String, created_at:String, social_media:String
)
