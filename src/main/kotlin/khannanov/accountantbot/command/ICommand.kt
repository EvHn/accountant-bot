package khannanov.accountantbot.command

import org.springframework.context.MessageSource
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.User
import java.util.*

abstract class ICommand(private val messageSource: MessageSource) {
    abstract fun execute(message: Message): SendMessage

    protected fun getUserAndChat(message: Message): Pair<User, Chat> = message.from to message.chat

    protected fun createLocalizedMessage(user: User, chat: Chat, code: String, vararg args: Any) = SendMessage().apply {
        chatId = "" + chat.id
        text = messageSource.getMessage(code, args, Locale(user.languageCode))
    }

    protected fun createMessage(chat: Chat, code: String) = SendMessage().apply {
        chatId = "" + chat.id
        text = code
    }

    protected fun getMessage(user: User, code: String, vararg args: Any) = messageSource.getMessage(code, args, Locale(user.languageCode))
}