package io.apinow

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.util.Random

class RampSimulation extends Simulation {
   val duration = Duration(System.getProperty("duration").toInt, "seconds")
   val users = System.getProperty("users").toInt

   val httpConf = http
      .baseUrl("http://" + System.getProperty("base") + "/v1")
      //.authorizationHeader("Basic c3BvcnRzaG9wOmI4YzhmNmFkLTE3NDgtNDI5OC1hMWMxLWMyY2Q1MzY4NmM2YQ==")
      .basicAuth("sportshop", "b8c8f6ad-1748-4298-a1c1-c2cd53686c6a")
      .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
      .acceptEncodingHeader("gzip, deflate")
      .acceptLanguageHeader("en-US,en;q=0.5")
      .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

   val sim = scenario(s"Ramp (Users=$users - Duration=$duration)")
      .feed(jsonFile("transactions.json").random)
      .exec(
         http("GET /transactions/{token}")
            .get("/transactions/${token}")
            .check(status.is(200))
            .check(jsonPath("$..token").is("${token}"))
            .check(jsonPath("$..client").is("sportshop"))
      )
      .pause(1 second, 3 seconds)
      .exec(
         http("GET /transactions/")
            .get(session => "/transactions/" + Random.alphanumeric.take(3).mkString)
            .check(status.is(404))
      )
      .pause(1 second, 3 seconds)

   setUp(
      sim.inject(
         rampUsersPerSec(1) to users during duration
      ).protocols(httpConf)
   )
}
