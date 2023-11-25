public class Player {
  private String name;
  private int dunkRating;
  private int threeRating;
  private int score;
  private int totalScore;

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

  public void setScore(int score) {
    this.score = score;
  }

  public void resetScore(){
    this.score = 0;
    this.totalScore = 0;
  }

  public void addScore(int score) {
    this.score += score;
    totalScore += score;
  }

  public int getTotalScore() {
    return totalScore;
  }

  public int getDunkerType() {
    if (dunkRating >= 100) {
      return 4;
    } else if (dunkRating >= 85) {
      return 3;
    } else if (dunkRating >= 70) {
      return 2;
    } else if (dunkRating >= 45) {
      return 1;
    } else return 0;
  }

  public String toString() {
    return name;
  }
}
