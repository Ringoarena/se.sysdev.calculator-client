package se.sysdev.calculator_client.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Body

private const val baseUrl = "https://calculator-lirxy4bywq-lz.a.run.app"

private val retrofit = Retrofit.Builder()
  .addConverterFactory(GsonConverterFactory.create())
  .baseUrl(baseUrl)
  .build()

data class CalculationRequest(
  val operation: String,
  val operands: List<Long>
)

data class CalculationResponse(
  val result: Long
)

interface CalculatorApiService {
  @POST("v2/calculation")
  suspend fun calculate(@Body r: CalculationRequest): Response<CalculationResponse>
}

object CalculatorApi {
  val retrofitService: CalculatorApiService by lazy {
    retrofit.create(CalculatorApiService::class.java)
  }
}
