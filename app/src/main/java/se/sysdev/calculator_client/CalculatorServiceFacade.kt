package se.sysdev.calculator_client

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class CalculatorServiceFacade {
  val baseUrl = "https://calculator-lirxy4bywq-lz.a.run.app"

  fun calculateAddition(a: Int, b: Int) {
    val url = "$baseUrl/v1/calculation/?operation=addition&oa=$a&ob=$b"
    Thread {
      val connection = URL(url).openConnection() as HttpURLConnection
      connection.requestMethod = "GET"
//      connection.setRequestProperty("Content-Type": "text/plain")
      connection.setRequestProperty("Accept", "text/plain")
      connection.connectTimeout = 10000
      connection.readTimeout = 10000
      try {
        val reader = InputStreamReader(connection.inputStream)
        reader.use { input ->
          val response = StringBuilder()
          val bufferedReader = BufferedReader(input)
          bufferedReader.forEachLine { response.append(it.trim()) }
          println("Success: ${response.toString()}")
        }
      } catch (e: Exception) {
        println("Failure: ${e.localizedMessage}")
      } finally {
        connection.disconnect()
      }
    }.start()
  }
}