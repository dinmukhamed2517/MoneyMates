package kz.sd.moneymates.network.gpt

import kz.sd.moneymates.gpt.model.ChatResponse
import kz.sd.moneymates.gpt.model.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface GptApi {
    @POST("chat/completions")
    suspend fun getPrompt(
        @Body requestBody: RequestBody,
    ): Response<ChatResponse>
}
