package controllers

import javax.inject._

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import com.github.xtwxy.dao._
import com.github.xtwxy.json._
import com.github.xtwxy.music._
import play.api.data._
import play.api.data.Form._
import play.api.data.format.Formats._
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

  def tree() = Action { implicit request =>
    val id: Long = if (request.hasBody) {
      val t = request.body.asFormUrlEncoded.get.get("id")
      if(t.isDefined && !t.get.isEmpty) t.get(0).toLong else 0
    } else {
      0
    }
    val nodes = treeDAO.selectTreeNodesByParentId(id)
      .map(t => TreeNodeVo(
        t.id,
        t.name,
        t.id.map(x => if (treeDAO.selectTreeNodeCountByParentId(x) > 0) "closed" else "open")
      ))
    Ok(Json.toJson(nodes))
  }

  def post() = Action { implicit request =>
    Ok(Json.toJson(Map("hello" -> "world")))
  }
}
