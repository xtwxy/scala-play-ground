package controllers

import javax.inject._

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import com.github.xtwxy.json._
import com.github.xtwxy.music._
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents,
                               @Named("music-actor") musicActor: ActorRef,
                               indexTemplate: views.html.index)(implicit ec: ExecutionContext)
  extends AbstractController(cc) with MusicJsonSupport {

  implicit val timeout: Timeout = 5.seconds

  /**
   * Create an Action to render an HTML page.
   * Ok(views.html.index())
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action {
    Ok(indexTemplate.render())
  }

  def getMusic() = Action.async { implicit request: Request[AnyContent] =>
    (musicActor ? "Do something").mapTo[List[MusicVo]].map { musics =>
      Ok(Json.toJson(musics))
    }
  }

  def music() = Action.async(parse.json) { implicit request =>
    if (request.contentType == Some("application/json")) {
      request.body.validate[MusicVo]
        .fold(errors => Future {
          BadRequest(Json.obj("status" -> "KO", "message" -> JsError.toJson(errors)))
        },
          x => {
            (musicActor ? x).mapTo[List[MusicVo]].map { musics =>
              Ok(Json.toJson(musics))
            }
          })
    } else {
      (musicActor ? "Some music?").mapTo[List[MusicVo]].map { musics =>
        Ok(Json.toJson(musics))
      }
    }
  }
}
