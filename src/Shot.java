public class Shot {

  public static final Shot regular = new Shot(125, 1, 'O');
  public static final Shot moneyBall = new Shot(150, 2, 'M');
  public static final Shot mountainDew = new Shot(250, 3, '3');

  private int difficulty;
  private int points;
  private char symbol;
  private int RNG;

  private Shot(int difficulty, int points, char symbol) {
    this.difficulty = difficulty;
    this.points = points;
    this.symbol = symbol;
  }

  public void takeShot(Player player) {
    RNG = (int) (Math.random() * difficulty);

    if (player.getThreeRating() > RNG) {
      System.out.print(this);
      player.addScore(points);
    } else {
      System.out.print("X ");
    }
  }

  @Override
  public String toString() {
    return symbol + " ";
  }
}
