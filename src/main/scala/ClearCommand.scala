import java.io.IOException
import java.nio.file.{Files, Paths}

// privateにすることで new による生成を抑止
class ClearCommand private(storeInfo: StoreInfo) extends Command {
  override def exec(args: Array[String]): Unit = {
    try {
      Files.write(Paths.get(storeInfo.storeName), "{}".getBytes())
    } catch {
      case e: IOException => println(e.getMessage)
    }
  }
}

// コンパニオンオブジェクト（ClearCommandクラスのコンストラクタを隠蔽するファクトリ）
object ClearCommand {
  def apply(storeInfo: StoreInfo): Command = new ClearCommand(storeInfo)
}
