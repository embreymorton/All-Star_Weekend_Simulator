public class Player {
  private String name;
  private int dunkRating;
  private int threeRating;
  private int score;

  public Player(String name, int threeRating, int dunkRating) {
    this.name = name;
    this.dunkRating = dunkRating;
    this.threeRating = threeRating;
  }

  public int getDunkRating() {
    return dunkRating;
  }

  public int getThreeRating() {
    return threeRating;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int s) {
    score = s;
  }

  public void addScore(int score) {
    this.score += score;
  }

  public int getDunkerType() {
    if (dunkRating >= 105) {
      return 4;
    } else if (dunkRating >= 95) {
      return 3;
    } else if (dunkRating >= 90) {
      return 2;
    } else if (dunkRating >= 85) {
      return 1;
    } else return 0;
  }

  public String toString() {
    return name;
  }
}
