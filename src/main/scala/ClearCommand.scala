// privateにすることで new による生成を抑止
class ClearCommand private(storeInfo: StoreInfo) extends Command {
  override def exec(args: Array[String]): Unit = {
    //    FIXME: logic
    println("CLEAR")
  }
}

// コンパニオンオブジェクト（ClearCommandクラスのコンストラクタを隠蔽するファクトリ）
object ClearCommand {
  def apply(storeInfo: StoreInfo): Command = new ClearCommand(storeInfo)
}
