package ru.effectivemobile.androidsdk.task4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class ChatViewModel : ViewModel() {

    private val _messages = MutableLiveData<List<ChatMessage>>()
    val messages: LiveData<List<ChatMessage>> = _messages

    private val scope = CoroutineScope(Dispatchers.Main + Job())

    init {
        _messages.value = listOf(
            ChatMessage("Привет! Добро пожаловать в чат 👋", false),
            ChatMessage("Спасибо! Как здесь всё работает?", true),
            ChatMessage("Это демо-чат для тестового задания", false),
            ChatMessage("Отлично! Можно отправлять сообщения", true)
        )
    }

    fun sendMessage(text: String) {
        val currentMessages = _messages.value?.toMutableList() ?: mutableListOf()
        val newMessage = ChatMessage(text, true)
        currentMessages.add(newMessage)
        _messages.value = currentMessages

        simulateReply()
    }

    private fun simulateReply() {
        scope.launch {
            delay(1500)
            val currentMessages = _messages.value?.toMutableList() ?: return@launch

            val replies = listOf(
                "Понял вас! 👍",
                "Интересно... расскажите подробнее",
                "Хорошо, спасибо за информацию",
                "👍 Отлично!",
                "Продолжайте в том же духе!",
                "Спасибо за сообщение"
            )
            val randomReply = replies.random()

            currentMessages.add(ChatMessage(randomReply, false))
            _messages.value = currentMessages
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}