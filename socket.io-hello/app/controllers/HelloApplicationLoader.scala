package controllers

import com.softwaremill.macwire._
import play.api._
import play.engineio.EngineIOController
import play.socketio.scaladsl.SocketIOComponents


class HelloApplicationLoader extends ApplicationLoader {
  override def load(context: ApplicationLoader.Context): Application =
    new HelloApplication(context).application
}

class HelloApplication(context: ApplicationLoader.Context)
  extends BuiltInComponentsFromContext(context)
    with AssetsComponents
    with SocketIOComponents {

  lazy val helloEngine = wire[HelloEngine]
  lazy val engineIOController: EngineIOController = helloEngine.controller

  override lazy val router = {
    val prefix = "/"
    wire[_root_.router.Routes]
  }
  override lazy val httpFilters = Nil
}
