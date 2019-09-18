object Main extends App {
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

  import scala.collection.mutable

  var cmdStore = mutable.Map.empty[String, String]

  val commands = Commands(StoreInfo("store.json"))

  println("Start!")
  while (true) {
    val cmds = io.StdIn.readLine().split(" ")

    // アプリ終了判定
    if (cmds(0) == "end") {
      commands.exec(cmds)
    }

    // ヘルプ
    if (cmds(0) == "help") {
      commands.exec(cmds)
    }

    if (cmds(0) == "") {
      usage()
    }

    // 保存
    if (cmds(0) == "save") {
      if (cmds.size != 3) {
        usage()
      } else {
        cmdStore += (cmds(1) -> cmds(2))
      }
    }

    // 取得
    if (cmds(0) == "get") {
      if (cmds.size != 2) {
        usage()
      } else {
        if (cmdStore.contains(cmds(1))) {
          println(cmdStore(cmds(1)))
        }
      }
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
