package controllers

import javax.inject._

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import com.github.xtwxy.dao._
import com.github.xtwxy.json._
import com.github.xtwxy.music._
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CrudController @Inject()(cc: ControllerComponents,
                               indexTemplate: views.html.crud)(implicit ec: ExecutionContext)
  extends AbstractController(cc) with MusicJsonSupport {

  implicit val timeout: Timeout = 5.seconds

  def index() = Action {
    Ok(indexTemplate.render())
  }

  def post() = Action { implicit request =>
    Ok(Json.toJson(Map("hello" -> "world")))
  }
}
