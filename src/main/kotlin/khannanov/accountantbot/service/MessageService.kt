package khannanov.accountantbot.service


import khannanov.accountantbot.command.ICommand
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.User

@Service
class MessageService(@Value("\${bot.auth.users}")
                     private val users: List<String>,
                     private val commands: Map<String, ICommand>) {

    fun processMessage(update: Update?): SendMessage {
        val message = update?.message?: null
        if(message != null && users.contains(message.from.userName) && message.isCommand && message.hasText()) {
            return process(message.from, message.chat, message.text)
        }
        return SendMessage()
    }

    private fun process(user: User, chat: Chat, message: String): SendMessage =
        commands[message.substringBefore(" ")]?.execute(user, chat, message)?: SendMessage()
}