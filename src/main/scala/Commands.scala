import java.io.PrintWriter
import java.nio.file.{Files, Paths}

// privateにすることで new による生成を抑止
class Commands private(commands: Map[String, Command]) {
  def exec(cmds: Array[String]): Unit = {
    if (cmds == null || cmds.length < 1) {
      print("no target")
      return
    }
    val cmdName = cmds.head
    val cmdArgs = cmds.tail
    commands(cmdName).exec(cmdArgs)
  }
}

// コンパニオンオブジェクト（Commandsクラスのコンストラクタを隠蔽するファクトリ）
object Commands {
  def apply(storeInfo: StoreInfo): Commands = {
    val store = Paths.get(storeInfo.storeName)
    if (Files.notExists(store)) {
      try(Files.createFile(store))
      val pw = new PrintWriter(storeInfo.storeName)
      pw.write("{}")
      pw.close()
    }

    new Commands(Map(
      "end" -> EndCommand(),
      "help" -> HelpCommand(),
      "clear" -> ClearCommand(storeInfo),
    ))
  }
}
