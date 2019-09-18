class Commands private(commands: Map[String, Command]) {
  def exec(cmds: Array[String]): Unit = {
    //    FIXME: check parameter
    val cmdName = cmds.head
    val cmdArgs = cmds.tail
    commands.get(cmdName).get.exec(cmdArgs)
  }
}

// コンパニオンオブジェクト（Commandsクラスのコンストラクタを隠蔽するファクトリ）
object Commands {
  def apply(storeInfo: StoreInfo): Commands = {
    val commands = Map(
      "end" -> new EndCommand,
      "help" -> new HelpCommand,
      "clear" -> new ClearCommand,
    )

    //    FIXME: file exists?

    new Commands(commands)
  }
}
