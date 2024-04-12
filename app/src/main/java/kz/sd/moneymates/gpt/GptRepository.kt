package kz.sd.moneymates.gpt

import androidx.lifecycle.LiveData
import kz.sd.moneymates.gpt.model.ChatResponse
import kz.sd.moneymates.gpt.model.Choice

interface GptRepository {

    suspend fun getPrompt(question: String): ChatResponse?

    val choiceLiveData: LiveData<Choice>?
}