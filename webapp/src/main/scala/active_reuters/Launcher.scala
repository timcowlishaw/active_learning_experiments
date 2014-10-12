import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ DefaultServlet, ServletContextHandler }
import org.eclipse.jetty.webapp.WebAppContext
package active_reuters {
  object Launcher {
    def main(args : Array[String]) = {
      val port = if(System.getenv("PORT") != null) System.getenv("PORT").toInt else 8080

      val server = new Server(port)
      val context = new WebAppContext()
      context.setContextPath("/")
      context.setResourceBase("src/main/webapp")

      context.addServlet(classOf[ReutersServlet], "/*")

      server.setHandler(context)

      server.start
      server.join
    }
  }
}
