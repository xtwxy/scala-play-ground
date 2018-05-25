package controllers

import javax.inject._

import akka.util.Timeout
import com.github.xtwxy.dao._
import com.github.xtwxy.json._
import com.github.xtwxy.music._
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

@Singleton
class TreeController @Inject()(cc: ControllerComponents,
                               treeDAO: TreeDAO,
                               indexTemplate: views.html.tree)(implicit ec: ExecutionContext)
  extends AbstractController(cc) with MusicJsonSupport {

  implicit val timeout: Timeout = 5.seconds

  def index() = Action {
    Ok(indexTemplate.render())
  }

  def tree(id: Long) = Action { implicit request =>
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
