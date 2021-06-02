package khannanov.accountantbot.command

import khannanov.accountantbot.data.Record
import khannanov.accountantbot.repository.IRecordRepository
import khannanov.accountantbot.unil.toMoneyString
import org.springframework.context.MessageSource
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User

@Component("/add")
class AddCommand(private val recordRepository: IRecordRepository, messageSource: MessageSource) : ICommand(messageSource) {

    private val regex = "^[0-9]{1,10}(\\.[0-9]{0,2}|)\$".toRegex()

    override fun execute(user: User, chat: Chat, message: String): SendMessage {
        val params = message.split(" ".toRegex(), 3)
        val sum = try {
            if (regex.matches(params[1]) && params.size >= 2) {
                params[1].toDouble()
            } else {
                return createLocalizedMessage(user, chat, "messages.invalid_input")
            }
        } catch (e: NumberFormatException) {
            return createLocalizedMessage(user, chat, "messages.invalid_input")
        }
        val description = if (params.size >= 3) params[2] else ""
        recordRepository.save(Record(user.id, chat.id, (sum * 100).toLong(), description))
        return createLocalizedMessage(user, chat, "messages.successfully_added", sum.toMoneyString(), description)
    }
}