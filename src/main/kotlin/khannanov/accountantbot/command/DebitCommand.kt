package khannanov.accountantbot.command

import khannanov.accountantbot.data.Record
import khannanov.accountantbot.repository.IRecordRepository
import khannanov.accountantbot.unil.toMoneyString
import org.springframework.context.MessageSource
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import java.util.*

open class DebitCommand(private val recordRepository: IRecordRepository, messageSource: MessageSource) : ICommand(messageSource) {

    private val sumRegex = "^(!-|\\+|)[0-9]{1,10}(\\.[0-9]{0,2}|)\$".toRegex()

    override fun execute(message: Message): SendMessage {
        val (user, chat) = getUserAndChat(message)
        val (sum, description) = parsMessage(message.text)?: return createLocalizedMessage(user, chat, "messages.invalid_input")
        recordRepository.save(Record(user.id, chat.id, (sum * 100).toLong(), description, Date(message.date.toLong())))
        return createLocalizedMessage(user, chat, "messages.successfully_added", sum.toMoneyString(), description)
    }

    private fun parsMessage(message: String): Pair<Double, String>? {
        val params = message.split(" ".toRegex(), 3)
        val sum = getSum(params)
        return if (sum != null) sum to getDescription(params) else null
    }

    private fun getSum(params: List<String>): Double? = try {
            val sumStr = params[1]
            if (sumRegex.matches(sumStr) && params.size >= 2) sumStr.getSum() else null
        } catch (e: NumberFormatException) {
            null
        }

    private fun getDescription(params: List<String>): String = if (params.size >= 3) params[2] else ""

    protected open fun String.getSum() = this.toDouble()
}