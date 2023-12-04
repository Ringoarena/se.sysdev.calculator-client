package se.sysdev.calculator_client.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.GET
import se.sysdev.calculator_client.dto.CalculationRequest
import se.sysdev.calculator_client.dto.CalculationResponse
import se.sysdev.calculator_client.dto.OperationsResponse

private const val baseUrl = "https://calculator-lirxy4bywq-lz.a.run.app"

private val retrofit = Retrofit.Builder()
  .addConverterFactory(GsonConverterFactory.create())
  .baseUrl(baseUrl)
  .build()

interface CalculatorApiService {
  @POST("v2/calculation")
  suspend fun calculate(@Body r: CalculationRequest): Response<CalculationResponse>
  @GET("v2/operations")
  suspend fun getOperations(): Response<OperationsResponse>
}

object CalculatorApi {
  val retrofitService: CalculatorApiService by lazy {
    retrofit.create(CalculatorApiService::class.java)
  }
}
