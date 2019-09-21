object Main extends App {
  val commands = Commands(StoreInfo("store.json"))

  println("Start!")
  while (true) {
    commands.exec(io.StdIn.readLine().split(" "))
  }
}
