trait Command {
  def exec(args: String*): Unit
}
