import scala.util.{Failure, Success}

// privateにすることで new による生成を抑止
class ClearCommand private(storeInfo: StoreInfo) extends Command with Helper {
  override def exec(args: String*): Unit = {
    // Helperトレイトに持たせたJSONファイルへの書き込みロジックを使用
    writeJson(storeInfo.storeName, Map[String, String]()) match {
      case Success(_) => // 何もしないが、case 句は必要
      case Failure(e) => println(e.getMessage)
    }
  }
}

// コンパニオンオブジェクト（ClearCommandクラスのコンストラクタを隠蔽するファクトリ）
object ClearCommand {
  def apply(storeInfo: StoreInfo): Command = new ClearCommand(storeInfo)
}
