package khannanov.accountantbot.config

import khannanov.accountantbot.command.CreditCommand
import khannanov.accountantbot.command.DebitCommand
import khannanov.accountantbot.command.DeleteCommand
import khannanov.accountantbot.command.ReportCommand
import khannanov.accountantbot.repository.IRecordRepository
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandConfig {

    @Bean("/cr")
    fun crCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = CreditCommand(recordRepository, messageSource)

    @Bean("/credit")
    fun creditCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = CreditCommand(recordRepository, messageSource)

    @Bean("/db")
    fun dbCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = DebitCommand(recordRepository, messageSource)

    @Bean("/debit")
    fun debitCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = DebitCommand(recordRepository, messageSource)

    @Bean("/dl")
    fun dlCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = DeleteCommand(recordRepository, messageSource)

    @Bean("/delete")
    fun deleteCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = DeleteCommand(recordRepository, messageSource)

    @Bean("/rp")
    fun rpCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = ReportCommand(recordRepository, messageSource)

    @Bean("/report")
    fun reportCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = ReportCommand(recordRepository, messageSource)
}