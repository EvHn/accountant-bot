package khannanov.accountantbot.config

import khannanov.accountantbot.command.*
import khannanov.accountantbot.repository.IRecordRepository
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Конфигурация команд
 */
@Configuration
class CommandConfig {

    @Bean("/mn")
    fun mnCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = MinusCommand(recordRepository, messageSource)

    @Bean("/minus")
    fun minusCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = MinusCommand(recordRepository, messageSource)

    @Bean("/pl")
    fun plCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = PlusCommand(recordRepository, messageSource)

    @Bean("/plus")
    fun plusCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = PlusCommand(recordRepository, messageSource)

    @Bean("/dl")
    fun dlCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = DeleteCommand(recordRepository, messageSource)

    @Bean("/delete")
    fun deleteCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = DeleteCommand(recordRepository, messageSource)

    @Bean("/ls")
    fun lsCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = ListCommand(recordRepository, messageSource)

    @Bean("/list")
    fun listCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = ListCommand(recordRepository, messageSource)

    @Bean("/rp")
    fun rpCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = ReportCommand(recordRepository, messageSource)

    @Bean("/report")
    fun reportCommand(recordRepository: IRecordRepository, messageSource: MessageSource) = ReportCommand(recordRepository, messageSource)
}