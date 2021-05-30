package khannanov.accountantbot.unil

fun Double.toMoneyString() = "%.2f".format(this)

fun Long.toMoneyString() = (this.toDouble() / 100).toMoneyString()