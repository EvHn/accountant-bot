package khannanov.accountantbot.command

import khannanov.accountantbot.repository.IRecordRepository
import khannanov.accountantbot.unil.toMoneyString
import org.springframework.context.MessageSource
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

class ReportCommand(private val recordRepository: IRecordRepository, messageSource: MessageSource) : ICommand(messageSource) {
    override fun execute(message: Message): SendMessage {
        val (user, chat) = getUserAndChat(message)
        val records = recordRepository.getByChatId(chat.id)
        val sum = records.sumOf { it.sum }
        return createMessage(chat,
            records.joinToString(separator = "\n") { "${it.sum.toMoneyString()} : ${it.description}" }
                    + getMessage(user, "messages.total_sum", sum.toMoneyString()))
    }
}