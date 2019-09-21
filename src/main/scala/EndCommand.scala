// privateにすることで new による生成を抑止
class EndCommand private() extends Command {
  override def exec(args: String*): Unit = {
    println("End!")
    sys.exit(-1)
  }
}

// コンパニオンオブジェクト（EndCommandクラスのコンストラクタを隠蔽するファクトリ）
object EndCommand {
  def apply(): Command = new EndCommand()
}
