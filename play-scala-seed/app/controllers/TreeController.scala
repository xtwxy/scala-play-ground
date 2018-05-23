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
class TreeController @Inject()(cc: ControllerComponents,
                               treeDAO: TreeDAO,
                               indexTemplate: views.html.tree)(implicit ec: ExecutionContext)
  extends AbstractController(cc) with MusicJsonSupport {

  implicit val timeout: Timeout = 5.seconds

  def index() = Action {
    Ok(indexTemplate.render())
  }

  def tree(parentId: Long) = Action { implicit request =>
    Ok(Json.toJson(treeDAO.selectTreeNodesByParentId(parentId)))
  }

  def post() = Action { implicit request =>
    Ok(Json.toJson(Map("hello" -> "world")))
  }
}
