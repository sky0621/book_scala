object Main extends App {
  val commands = Commands(StoreInfo("store.json"))

  import scala.collection.mutable
  var cmdStore = mutable.Map.empty[String, String]

  def usage(): Unit = println(
    """
      |[usage]
      |キーバリュー形式で文字列情報を管理するコマンドです。
      |以下のサブコマンドが利用可能です。
      |
      |list   ... 保存済みの内容を一覧表示します。
      |save   ... keyとvalueを渡して保存します。
      |get    ... keyを渡してvalueを表示します。
      |remove ... keyを渡してvalueを削除します。
      |help   ... ヘルプ情報（当内容と同じ）を表示します。
      |
      |""".stripMargin)

  println("Start!")
  while (true) {
    val cmds = io.StdIn.readLine().split(" ")

    if (cmds(0) == "end" || cmds(0) == "help" || cmds(0) == "clear" || cmds(0) == "save" || cmds(0) == "get") {
      commands.exec(cmds)
    }

    // 削除
    if (cmds(0) == "remove") {
      if (cmds.size != 2) {
        usage()
      } else {
        cmdStore -= cmds(1)
      }
    }

    // 一覧
    if (cmds(0) == "list") {
      println("\"key\",\"value\"")
      for ((k, v) <- cmdStore) println("\"%s\",\"%s\"".format(k, v))
    }
  }
}
