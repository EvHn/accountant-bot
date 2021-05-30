package khannanov.accountantbot

import khannanov.accountantbot.service.MessageService
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

@Component
class AccountantBot : TelegramLongPollingBot() {

    @Autowired
    private lateinit var messageService: MessageService

    @Value("\${bot.username}")
    private var botUsername: String = ""

    @Value("\${bot.token}")
    private var botToken: String = ""

    override fun getBotToken(): String = botToken

    override fun getBotUsername(): String = botUsername

    override fun onUpdateReceived(update: Update?) {
        if (update != null) {
            try {
                execute(messageService.processMessage(update))
            } catch (e: TelegramApiException) {
            }
        }
    }
}