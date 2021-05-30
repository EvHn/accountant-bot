package khannanov.accountantbot.command

import khannanov.accountantbot.data.Record
import khannanov.accountantbot.repository.IRecordRepository
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User

@Component("/add")
class AddCommand(private val recordRepository: IRecordRepository) : ICommand {
    override fun execute(user: User, chat: Chat, message: String): SendMessage {
        val params = message.split(" ".toRegex(), 3)
        val sum = try {
            if (params.size >= 2) {
                params[1].toLong()
            } else {
                return sendToChat(chat, "Not enough parameters")
            }
        } catch (e: NumberFormatException) {
            return sendToChat(chat, "Invalid input")
        }
        val description = if (params.size >= 3) params[2] else ""
        recordRepository.save(Record(user.id, chat.id, sum, description))
        return sendToChat(chat, "Added new record with sum $sum and comment '$description'")
    }
}