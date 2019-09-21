import scala.util.{Failure, Success}

// privateにすることで new による生成を抑止
class SaveCommand private(storeInfo: StoreInfo) extends Command with Helper {
  override def exec(args: String*): Unit = {
    if (args.size > 1) {
      getJson(storeInfo.storeName) match {
        // 既存の json に、 + (key -> value) でコンソール入力されたキーバリューが追加される
        case Success(json) => writeJson(storeInfo.storeName, json + (args(0) -> args(1))) match {
          case Success(_) => // 何もしないが、case 句は必要
          case Failure(e) => println(e.getMessage)
        }
        case Failure(e) => println(e.getMessage)
      }
    }
  }
}

// コンパニオンオブジェクト（SaveCommandクラスのコンストラクタを隠蔽するファクトリ）
object SaveCommand {
  def apply(storeInfo: StoreInfo): Command = new SaveCommand(storeInfo)
}
