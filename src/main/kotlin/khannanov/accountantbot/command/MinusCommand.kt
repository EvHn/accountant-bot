package khannanov.accountantbot.command

import khannanov.accountantbot.repository.IRecordRepository
import org.springframework.context.MessageSource

/**
 * Добавление отрицательной записи
 */
class MinusCommand(recordRepository: IRecordRepository, messageSource: MessageSource) : PlusCommand(recordRepository, messageSource) {
    override fun String.getSum() = -this.toDouble()
}