import java.nio.file.{Files, Paths}

// privateにすることで new による生成を抑止
class Commands private(commands: Map[String, Command]) {
  def exec(cmds: Array[String]): Unit = {
    if (commands.contains(cmds.head)) {
      commands(cmds.head).exec(cmds.tail: _*) // 可変長引数の定義に対して Array<String> を渡す際は _* を使う
    } else {
      commands("help").exec(null)
    }
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
        "get" -> GetCommand(storeInfo),
        "remove" -> RemoveCommand(storeInfo),
        "list" -> ListCommand(storeInfo)
      )
    )
  }
}
