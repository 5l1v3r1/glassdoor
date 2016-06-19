package io.glassdoor.plugin

case class PluginData(name:String, kind:String, dependencies:Array[String], changes:Array[String], commands:Array[String], pluginClass:String, pluginEnvironment:Option[Map[String,String]])