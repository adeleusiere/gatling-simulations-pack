package cfcamp2016

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class ConstantSimulation extends Simulation {
  val duration = Duration(System.getProperty("duration").toInt - 5, "seconds")
  val users = System.getProperty("users").toInt

  val httpConf = http
   .baseURL("http://" + System.getProperty("base"))
   .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
   .acceptEncodingHeader("gzip, deflate")
   .acceptLanguageHeader("en-US,en;q=0.5")
   .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:42.0) Gecko/20100101 Firefox/42.0")

  val sim = scenario(s"Constant (Users=$users - Duration=$duration)")
      .exec(
         http("Get http://" + System.getProperty("base") + "/cfcamp2016/demo1/")
            .get("/cfcamp2016/demo1/")
            .check(
               status.is(200),
               substring("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
            )
      )

   setUp(
      sim.inject(
         rampUsersPerSec(1) to users during Duration(5, "seconds"),
         constantUsersPerSec(users) during duration
      ).protocols(httpConf)
   )
}
