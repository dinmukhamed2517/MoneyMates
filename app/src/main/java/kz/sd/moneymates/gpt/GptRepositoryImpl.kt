package kz.sd.moneymates.gpt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kz.sd.moneymates.gpt.model.ChatResponse
import kz.sd.moneymates.gpt.model.Choice
import kz.sd.moneymates.gpt.model.Message
import kz.sd.moneymates.gpt.model.RequestBody
import kz.sd.moneymates.network.gpt.GptApi
import javax.inject.Inject

class GptRepositoryImpl @Inject constructor(
    private val api: GptApi
) : GptRepository {



    private val _choicesLiveData = MutableLiveData<Choice>()
    override val choiceLiveData: LiveData<Choice>? = _choicesLiveData
    override suspend fun getPrompt(question: String): ChatResponse? {

        val requestBody = RequestBody(
            messages = listOf(
                Message(
                    role = "system",
                    content = "Imagine that you are Pikachu and you're expert in financial education. Your main auditory is kids, so try to answer as simple as possible" +
                            "Kid will ask you questions about how to save money properly, how to start investing some similar stuff. You need to answer as well experienced teacher." +
                            "Always answer briefly in 2-3 sentences. If you are not able to fit your answer in 2 -3 sentences ask user to text additional questions"

                ),
                Message(
                    role = "user",
                    content = "$question"
                )
            ),
        )
        val response = api.getPrompt(requestBody)
        return if (response.isSuccessful) response.body()
        else throw Exception(response.message())
    }

}