package khannanov.accountantbot.repository

import khannanov.accountantbot.data.Record
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IRecordRepository : CrudRepository<Record, Long> {
    fun getByChatId(chatId: Long): List<Record>
}