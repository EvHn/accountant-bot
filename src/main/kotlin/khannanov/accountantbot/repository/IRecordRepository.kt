package khannanov.accountantbot.repository

import khannanov.accountantbot.data.Record
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface IRecordRepository : CrudRepository<Record, Long> {
    fun getByChatId(chatId: Long): List<Record>

    @Query(nativeQuery = true, value = "select * from Record where chat_id = :chatId order by date desc limit 1")
    fun findLast(@Param("chatId") chatId: Long): Record?
}