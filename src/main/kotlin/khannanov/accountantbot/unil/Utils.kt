package khannanov.accountantbot.unil

/**
 * Double в денежный формат
 */
fun Double.toMoneyString() = "%.2f".format(this)

/**
 * Long в денежный формат (деление на 100)
 */
fun Long.toMoneyString() = (this.toDouble() / 100).toMoneyString()