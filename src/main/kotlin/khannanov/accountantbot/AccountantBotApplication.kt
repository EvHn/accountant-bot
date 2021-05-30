package khannanov.accountantbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AccountantBotApplication

fun main(args: Array<String>) {
	runApplication<AccountantBotApplication>(*args)
}
