import java.nio.file.{Files, Paths}

// privateにすることで new による生成を抑止
class Commands private(commands: Map[String, Command]) {
  def exec(cmds: Array[String]): Unit = {
    if (cmds == null || cmds.length < 1) {
      print("no target")
      return
    }
    commands(cmds.head).exec(cmds.tail: _*) // 可変長引数の定義に対して Array<String> を渡す際は _* を使う
  }
}

// コンパニオンオブジェクト（Commandsクラスのコンストラクタを隠蔽するファクトリ）
object Commands {
  def apply(storeInfo: StoreInfo): Commands = {
    if (Files.notExists(Paths.get(storeInfo.storeName))) {
      ClearCommand(storeInfo).exec(null)
    }

    new Commands(
      Map(
        "end" -> EndCommand(),
        "help" -> HelpCommand(),
        "clear" -> ClearCommand(storeInfo),
        "save" -> SaveCommand(storeInfo),
        "get" -> GetCommand(storeInfo)
      )
    )
  }
}
