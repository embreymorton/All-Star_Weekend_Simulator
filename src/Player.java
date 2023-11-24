public class Player {
  private String name;
  private int dunkRating;
  private int threeRating;
  private int score;

  public enum PlayerType {
    SHOOTER,
    DUNKER,
    BOTH
  }

  private PlayerType type;

  public Player(String name, int rating, PlayerType type) {
    if (name == null) {
      throw new IllegalArgumentException("Players need a name");
    }
    this.name = name;

    switch (type) {
      case SHOOTER -> {
        threeRating = rating;
        this.type = PlayerType.SHOOTER;
      }
      case DUNKER -> {
        dunkRating = rating;
        this.type = PlayerType.DUNKER;
      }
    }
  }

  public Player(String name, int threeRating, int dunkRating) {
    this.name = name;
    this.dunkRating = dunkRating;
    this.threeRating = threeRating;

    type = PlayerType.BOTH;
  }

  public String getName() {
    return name;
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

  public PlayerType getType() {
    return type;
  }

  public int getDunkerType() {
    if (dunkRating >= 105) {
      return 3;
    } else if (dunkRating >= 95) {
      return 2;
    } else if (dunkRating >= 90) {
      return 1;
    } else return 0;
  }

  public String toString() {
    return name;
  }
}
