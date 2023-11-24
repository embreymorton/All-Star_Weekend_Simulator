import java.util.Random;

public class Dunk {

  public static final Dunk legendary = new Dunk("Legendary", 120, 50);
  public static final Dunk elite = new Dunk("Elite", 200, 45);
  public static final Dunk pro = new Dunk("Pro", 80, 40);
  public static final Dunk basic = new Dunk("Basic", 60, 35);

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
    randomized_points = Math.max(35, randomized_points);

    return randomized_points - attempt;
  }

  public String toString() {
    return name;
  }

  //  private String name;
  //  private int difficulty;
  //  private int dunkScore;
  //  Random random = new Random();
  //  int randNum;
  //  int ranDunk;
  //
  //  public enum DunkType {
  //    SAFETY,
  //    PRO,
  //    ELITE,
  //    LEGENDARY
  //  }
  //
  //  private DunkType type;
  //
  //  public Dunk(String n, int d, int s, DunkType t) {
  //    name = n;
  //    difficulty = d;
  //    dunkScore = s;
  //    type = t;
  //  }
  //
  //  public String getName() {
  //    return name;
  //  }
  //
  //  public int getDifficulty() {
  //    return difficulty;
  //  }
  //
  //  public int getDunkScore(int attempt) {
  //    randNum = random.nextInt(10) - 5;
  //    ranDunk = dunkScore + randNum;
  //    if (ranDunk > 50) {
  //      ranDunk = 50;
  //    }
  //    if (ranDunk < 35) {
  //      ranDunk = 35;
  //    }
  //
  //    switch (type) {
  //      case LEGENDARY:
  //        if (attempt == 1) return 50;
  //        else return ranDunk;
  //      case ELITE:
  //        if (attempt == 1) return ranDunk;
  //        else if (attempt == 2) return ranDunk - 1;
  //        else return ranDunk - 2;
  //      case PRO:
  //        if (attempt == 1) return ranDunk;
  //        else if (attempt == 2) return ranDunk - 2;
  //        else return ranDunk - 4;
  //      case SAFETY:
  //        return ranDunk;
  //      default:
  //        return 30;
  //    }
  //  }
}
