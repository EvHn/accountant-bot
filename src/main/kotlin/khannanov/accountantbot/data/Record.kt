package khannanov.accountantbot.data

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Record(var userId: Long = 0, var chatId: Long = 0, var sum: Long = 0,
             var description: String = "", var date: Date = Date(), @Id @GeneratedValue var id: Long = 0)