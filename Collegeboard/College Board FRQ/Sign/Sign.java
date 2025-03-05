public class Sign {
  private int width;
  private String message;
  public Sign(int width, String message) {
    this.width = width;
    this.message = message;
  }
  public int numberOfLines() {
    int line = message.length() / width;
    if (message.length() % width > 0) {
      line++;
    }
    return line;
  }
  public String getLines() {
    if (message.length() == 0) {
      return null;
    }
    String lines = "";
    String remMsg = message;
    while (remMsg.length() > width) {
      lines += remMsg.subString(0, width);
      lines += ";";
      remMsg = remMsg.substring(width);
    }
    lines += remMsg;
    return lines;
  }
}
