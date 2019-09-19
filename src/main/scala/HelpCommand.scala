// privateにすることで new による生成を抑止
class HelpCommand private extends Command {
  override def exec(args: Array[String]): Unit = println(
    """
      |[usage]
      |キーバリュー形式で文字列情報を管理するコマンドです。
      |以下のサブコマンドが利用可能です。
      |
      |save   ... keyとvalueを渡して保存します。
      |get    ... keyを渡してvalueを表示します。
      |remove ... keyを渡してvalueを削除します。
      |list   ... 保存済みの内容を一覧表示します。
      |clear  ... 保存済みの内容を初期化します。
      |help   ... ヘルプ情報（当内容と同じ）を表示します。
      |end    ... アプリを終了します。
      |
      |""".stripMargin)
}

// コンパニオンオブジェクト（HelpCommandクラスのコンストラクタを隠蔽するファクトリ）
object HelpCommand {
  def apply(): Command = new HelpCommand()
}
