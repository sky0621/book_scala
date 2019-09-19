import java.nio.file.{Files, Paths}

// privateにすることで new による生成を抑止
class Commands private(commands: Map[String, Command]) {
  def exec(cmds: Array[String]): Unit = {
    if (cmds == null || cmds.size < 1) {
      print("no target")
      return
    }
    val cmdName = cmds.head
    val cmdArgs = cmds.tail
    commands.get(cmdName).get.exec(cmdArgs)
  }
}

// コンパニオンオブジェクト（Commandsクラスのコンストラクタを隠蔽するファクトリ）
object Commands {
  def apply(storeInfo: StoreInfo): Commands = {
    val commands = Map(
      "end" -> EndCommand(),
      "help" -> HelpCommand(),
      "clear" -> ClearCommand(storeInfo),
    )

    if (Files.notExists(Paths.get(storeInfo.storeName))) {
      commands.get("clear").get.exec(null)
    }

    new Commands(commands)
  }
}
