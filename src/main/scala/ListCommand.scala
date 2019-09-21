import scala.util.{Failure, Success}

// privateにすることで new による生成を抑止
class ListCommand private(storeInfo: StoreInfo) extends Command with Helper {
  override def exec(args: String*): Unit = {
    getJson(storeInfo.storeName) match {
      case Success(json) =>
        println("\"key\",\"value\"")
        json.foreach { case (k, v) => println("\"%s\",\"%s\"".format(k, v)) }
      case Failure(e) => println(e.getMessage)
    }
  }
}

// コンパニオンオブジェクト（ListCommandクラスのコンストラクタを隠蔽するファクトリ）
object ListCommand {
  def apply(storeInfo: StoreInfo): Command = new ListCommand(storeInfo)
}
