public class Player {
  private String name;
  private int dunkRating;
  private int threeRating;
  private int score = 0;
  private int dunkScore = 0;

  public enum PlayerType {
    SHOOTER,
    DUNKER,
    BOTH
  }

  private PlayerType type;

  public Player(String name, int rating, PlayerType type) {
    if (name == null) {
      throw new IllegalArgumentException("Player need a name");
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
    this.dunkScore += score;
  }

  public int getDunkScore() {
    return dunkScore;
  }

  public void setDunkScore(int ds) {
    dunkScore = ds;
  }

  public PlayerType getType() {
    return type;
  }

  public int getDunkerType() {
    if (dunkRating >= 105) {
      return 4;
    } else if (dunkRating >= 95) {
      return 3;
    } else if (dunkRating >= 90) {
      return 2;
    } else return 1;
  }

  public String toString() {
    return name;
  }
}
