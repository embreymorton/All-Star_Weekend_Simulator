import java.util.Random;

public class Dunk {

  public static final Dunk legendary = new Dunk("Legendary", 120, 50);
  public static final Dunk elite = new Dunk("Elite", 100, 45);
  public static final Dunk pro = new Dunk("Pro", 80, 40);
  public static final Dunk safety = new Dunk("Safety", 60, 35);
  public static final Dunk basic = new Dunk("Basic", 25, 30);

  private String name;
  private int difficulty;
  private int base_points;
  private int randomized_points;
  private int RNG;
  private Random random;

  public Dunk(String name, int difficulty, int base_points) {
    this.name = name;
    this.difficulty = difficulty;
    this.base_points = base_points;
    this.random = new Random();
  }

  public boolean madeDunk(Player player) {
    RNG = difficulty - (int) (Math.random() * 15);
    if (player.getDunkRating() > RNG) return true;
    return false;
  }

  public int getScore(int attempt) {
    RNG = random.nextInt(7) - 3;
    randomized_points = base_points + RNG;
    randomized_points = Math.min(50, randomized_points);
    randomized_points = Math.max(30, randomized_points);

    return randomized_points - attempt;
  }

  public String toString() {
    return name;
  }


}
