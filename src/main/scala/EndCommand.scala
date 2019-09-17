class EndCommand extends Command {
  override def exec(args: Array[String]): Unit = {
    println("End!")
    sys.exit(-1)
  }
}
