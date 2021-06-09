package khannanov.accountantbot.command

import khannanov.accountantbot.repository.IRecordRepository
import khannanov.accountantbot.unil.toMoneyString
import org.springframework.context.MessageSource
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

class ReportCommand(private val recordRepository: IRecordRepository, messageSource: MessageSource) : ICommand(messageSource) {
    override fun execute(message: Message): SendMessage {
        val reports = recordRepository.getByChatId(message.chatId)
        val creditSum = reports.filter { it.sum < 0 }.sumOf { it.sum }
        val debitSum = reports.filter { it.sum > 0 }.sumOf { it.sum }
        return createMessage(message.chat,
            "Credit: ${creditSum.toMoneyString()}\nDebit: ${debitSum.toMoneyString()}\nTotal sum: ${(debitSum + creditSum).toMoneyString()}")
    }
}