import scala.util.{Failure, Success}

// privateにすることで new による生成を抑止
class GetCommand private(storeInfo: StoreInfo) extends Command with Helper {
  override def exec(args: String*): Unit = {
    if (args.size > 0) {
      getJson(storeInfo.storeName) match {
        case Success(json) => {
          if (json.contains(args(0))) {
            println(json(args(0)))
          }
        }
        case Failure(e) => println(e.getMessage)
      }
    }
  }
}

// コンパニオンオブジェクト（GetCommandクラスのコンストラクタを隠蔽するファクトリ）
object GetCommand {
  def apply(storeInfo: StoreInfo): Command = new GetCommand(storeInfo)
}
