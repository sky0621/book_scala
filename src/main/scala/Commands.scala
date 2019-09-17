class Commands(implicit storeInfo: StoreInfo)

object Commands {
  def apply(implicit storeInfo: StoreInfo): Commands = new Commands()
}
