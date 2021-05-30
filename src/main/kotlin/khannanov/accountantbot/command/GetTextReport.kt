package khannanov.accountantbot.command

import khannanov.accountantbot.repository.IRecordRepository
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User

@Component("/getTextReport")
class GetTextReport(private val recordRepository: IRecordRepository) : ICommand {
    override fun execute(user: User, chat: Chat, message: String): SendMessage {
        val records = recordRepository.getByChatId(chat.id)
        val sum = records.sumOf { it.sum }
        return sendToChat(chat,
            records.joinToString(separator = "\n") { it.sum.toString() + " : " + it.description } + "\nTotal sum: $sum")
    }
}