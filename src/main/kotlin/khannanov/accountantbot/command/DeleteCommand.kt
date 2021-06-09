package khannanov.accountantbot.command

import khannanov.accountantbot.repository.IRecordRepository
import org.springframework.context.MessageSource
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

/**
 * Удаление последней записи
 */
class DeleteCommand(private val recordRepository: IRecordRepository, messageSource: MessageSource) : ICommand(messageSource) {
    override fun execute(message: Message): SendMessage {
        val last = recordRepository.findLast(message.chatId)
        if (last != null) {
            recordRepository.delete(last)
        }
        return SendMessage()
    }
}