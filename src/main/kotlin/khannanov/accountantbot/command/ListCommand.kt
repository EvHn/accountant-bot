package khannanov.accountantbot.command

import khannanov.accountantbot.repository.IRecordRepository
import khannanov.accountantbot.unil.toMoneyString
import org.springframework.context.MessageSource
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

/**
 * Показать записи
 */
class ListCommand(private val recordRepository: IRecordRepository, messageSource: MessageSource) : ICommand(messageSource) {
    override fun execute(message: Message): SendMessage {
        val (_, chat) = getUserAndChat(message)
        val records = recordRepository.getByChatId(chat.id)
        return createMessage(chat, records.mapIndexed { i, r -> "${i + 1}) ${r.sum.toMoneyString()} : ${r.description}"}.joinToString(separator = "\n"))
    }
}