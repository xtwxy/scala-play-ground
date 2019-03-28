package controllers

import com.github.apuex.play.bodyparser.GsonBody
import com.github.apuex.springbootsolution.runtime.QueryCommand
import javax.inject._
import play.api.mvc._

@Singleton
class QueryCommandController @Inject()(cc: ControllerComponents, gson: GsonBody) extends AbstractController(cc) {
  //implicit val writable = (Writeable[String](a => ByteString.fromArrayUnsafe(a.getBytes("utf-8"))))

  def default = Action { request =>
    val body = QueryCommand.newBuilder()
    body.setPageNumber(1)
      .setPagingState("paging-state")
      .setRowsPerPage(100)

    gson.print(body.build())
  }

  def query: Action[QueryCommand] = Action(gson.parser(classOf[QueryCommand])) { request =>
    gson.print(request.body)
  }

}
