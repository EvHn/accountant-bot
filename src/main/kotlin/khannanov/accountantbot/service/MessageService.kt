package khannanov.accountantbot.service

import khannanov.accountantbot.command.ICommand
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Сервис обработки сообщений
 */
@Service
class MessageService(@Value("\${bot.auth.users}")
                     private val users: List<String>,
                     private val commands: Map<String, ICommand>) {

    fun processMessage(update: Update?): SendMessage {
        val message = update?.message?: null
        if(message != null && users.contains(message.from.userName) && message.isCommand && message.hasText()) {
            return process(message)
        }
        return SendMessage()
    }

    private fun process(message: Message): SendMessage =
        commands[message.text.substringBefore(" ")]?.execute(message)?: SendMessage()
}