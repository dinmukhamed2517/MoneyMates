package kz.sd.moneymates.gpt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kz.sd.moneymates.base.BaseViewModel
import kz.sd.moneymates.gpt.model.ChatResponse
import javax.inject.Inject


@HiltViewModel
class GptViewModel @Inject constructor(
    private val repository: GptRepositoryImpl
) : BaseViewModel() {
    private var _chatResponseLiveData = MutableLiveData<ChatResponse?>()
    var chatResponseLiveData: LiveData<ChatResponse?> = _chatResponseLiveData
    fun getPrompt(question: String) {
        launch(
            request = {
                repository.getPrompt(question)
            },
            onSuccess = {
                _chatResponseLiveData.postValue(it)
            }
        )
    }
}



