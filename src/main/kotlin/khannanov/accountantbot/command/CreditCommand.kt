package khannanov.accountantbot.command

import khannanov.accountantbot.repository.IRecordRepository
import org.springframework.context.MessageSource

class CreditCommand(recordRepository: IRecordRepository, messageSource: MessageSource) : DebitCommand(recordRepository, messageSource) {
    override fun String.getSum() = -this.toDouble()
}