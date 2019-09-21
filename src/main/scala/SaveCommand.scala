import scala.util.{Failure, Success}

// privateにすることで new による生成を抑止
class SaveCommand private(storeInfo: StoreInfo) extends Command with Helper {
  override def exec(args: String*): Unit = {
    getJson(storeInfo.storeName) match {
      case Success(json) => writeJson(storeInfo.storeName, json + (args(0) -> args(1))) match {
        case Success(_) =>
        case Failure(e) => println(e.getMessage)
      }
      case Failure(e) => println(e.getMessage)
    }
  }
}

// コンパニオンオブジェクト（SaveCommandクラスのコンストラクタを隠蔽するファクトリ）
object SaveCommand {
  def apply(storeInfo: StoreInfo): Command = new SaveCommand(storeInfo)
}
