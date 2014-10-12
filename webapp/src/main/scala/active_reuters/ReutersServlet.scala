import org.scalatra._

package active_reuters {
  class ReutersServlet extends ScalatraServlet {
    get("/") {
      "Hello World!" 
    }  
  }
}
