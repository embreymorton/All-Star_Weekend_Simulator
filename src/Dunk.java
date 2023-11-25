import java.util.Random;

public class Dunk {

  public static final Dunk legendary = new Dunk("Legendary", 150, 50);
  public static final Dunk elite = new Dunk("Elite", 120, 45);
  public static final Dunk pro = new Dunk("Pro", 80, 40);
  public static final Dunk athletic = new Dunk("Athletic", 60, 35);
  public static final Dunk basic = new Dunk("Basic", 25, 30);

  private String name;
  private int difficulty;
  private int base_score;
  private int randomized_score;
  private int RNG;
  private Random random;

  public Dunk(String name, int difficulty, int base_score) {
    this.name = name;
    this.difficulty = difficulty;
    this.base_score = base_score;
    this.random = new Random();
  }

  public boolean madeDunk(Player player) {
    RNG = (int) (Math.random() * difficulty);
    if (player.getDunkRating() > RNG) return true;
    return false;
  }

  public int getScore(int attempt) {
    RNG = random.nextInt(11) - 5;

    randomized_score = base_score + RNG;
    randomized_score = Math.min(50, randomized_score);
    randomized_score = Math.max(25, randomized_score);

    RNG = (int) (Math.random() * attempt);

    return randomized_score - RNG;
  }

  public String toString() {
    return name;
  }
}
