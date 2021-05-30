package khannanov.accountantbot.command

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User

interface ICommand {
    fun execute(user: User, chat: Chat, message: String): SendMessage
    fun sendToChat(chat: Chat, answer: String) = SendMessage().apply {
        chatId = "" + chat.id
        text = answer
    }
}