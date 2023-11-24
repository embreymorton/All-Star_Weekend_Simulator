import java.util.*;

public class DC {

  private List<Player> contestants;
  private Map<Integer, Dunk> dunkTypes;
  private Dunk current_dunk;
  private int dunker_type;
  private int dunk_score;
  private Player winner;

  public DC(List<Player> players) {
    contestants = new ArrayList<>(players);

    // Can make these lists of dunk_types eventually
    dunkTypes = new HashMap<>();
    dunkTypes.put(0, Dunk.basic);
    dunkTypes.put(1, Dunk.pro);
    dunkTypes.put(2, Dunk.elite);
    dunkTypes.put(3, Dunk.legendary);
  }

  public List<Player> getContestants() {
    return contestants;
  }

  private void resetScores() {
    for (Player p : contestants) {
      p.setScore(0);
    }
  }

  private void sim_turn(Player player) {
    System.out.println(player);

    dunker_type = player.getDunkerType();
    current_dunk = dunkTypes.get(dunker_type);
    dunk_score = 25;

    for (int i = 0; i < 3; i++) {
      if (i == 2) current_dunk = dunkTypes.get(Math.max(0, dunker_type - 1));
      System.out.print("Attempt " + (i + 1) + ": ");

      if (current_dunk.madeDunk(player)) {
        System.out.println("Successful " + current_dunk + " dunk");
        dunk_score = current_dunk.getScore(i);
        break;
      }

      System.out.println("Unsuccessful " + current_dunk + " dunk");
    }
    player.addScore(dunk_score);
    System.out.println(dunk_score + "\n");
  }

  private void first_round() {
    resetScores();

    System.out.println("Dunk One\n");
    for (Player p : contestants) {
      sim_turn(p);
    }
    System.out.println("Dunk Two\n");
    for (Player p : contestants) {
      sim_turn(p);
    }

    Collections.sort(contestants, new PlayerComparator());
    contestants = contestants.subList(0, 2);
  }

  private void second_round() {
    resetScores();

    System.out.println("Dunk One\n");
    for (Player p : contestants) {
      sim_turn(p);
    }
    System.out.println("Dunk Two\n");
    for (Player p : contestants) {
      sim_turn(p);
    }

    Collections.sort(contestants, new PlayerComparator());
    winner = contestants.get(0);
  }

  public void simulate_DC() {
    System.out.println("Dunk Contest Simulation");
    System.out.println("-------------------------");
    System.out.println("Simulating First Round");
    System.out.println("-------------------------");
    first_round();
    System.out.println("-------------------------");
    System.out.println("Simulating Second Round");
    System.out.println("-------------------------");
    second_round();
    System.out.println("Dunk Contest Winner: " + winner);
  }
}
