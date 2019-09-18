class StoreInfo private(val storeName: String = "store.json")

// コンパニオンオブジェクト（StoreInfoクラスのコンストラクタを隠蔽するファクトリ）
object StoreInfo {
  def apply(storeName: String): StoreInfo = new StoreInfo(storeName)
}
