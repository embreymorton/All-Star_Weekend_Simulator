import java.util.*;

public class DunkContest {
  private List<Player> contestants;
  private Map<Integer, Dunk> dunkTypes;
  private Dunk current_dunk;
  private int dunker_type;
  private int dunk_score;
  private Player winner;

  public DunkContest(List<Player> players) {
    contestants = new ArrayList<>(players);

    // Can make these lists of dunk_types eventually
    dunkTypes = new HashMap<>();
    dunkTypes.put(0, Dunk.basic);
    dunkTypes.put(1, Dunk.athletic);
    dunkTypes.put(2, Dunk.pro);
    dunkTypes.put(3, Dunk.elite);
    dunkTypes.put(4, Dunk.legendary);
  }

  private void printContestants(int round) {
    if (round == 1) System.out.print("Contestants: ");
    else System.out.print("Advancing: ");
    for (Player p : contestants) {
      System.out.print(p);
      if (round == 2) System.out.print("(" + p.getScore() + ")");
      if (contestants.indexOf(p) != contestants.size() - 1) System.out.print(" -- ");
    }
    System.out.println();
  }

  public Player getWinner() {
    return winner;
  }

  private void resetScores(Boolean start) {
    for (Player p : contestants) {
      if(start) p.resetScore();
      else p.setScore(0);
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

  private void sim_round(int round) {
    if(round == 1) resetScores(true);
    else resetScores(false);

    for (int i = 1; i <= 2; i++) {
      if (i == 1) System.out.println("--Dunk One--\n");
      else System.out.println("--Dunk Two--\n");
      for (Player p : contestants) {
        sim_turn(p);
      }
    }

    Collections.sort(contestants, new PlayerComparator());
    if (round == 1) contestants = contestants.subList(0, 2);
    else winner = contestants.get(0);
  }

  public void simulate_DC() {
    System.out.println("Dunk Contest Simulation");
    printContestants(1);
    System.out.println("-------------------------");
    System.out.println("Simulating First Round");
    System.out.println("-------------------------");
    sim_round(1);
    System.out.println("-------------------------");
    System.out.println("Simulating Second Round");
    printContestants(2);
    System.out.println("-------------------------");
    sim_round(2);
    System.out.println(
        "-- Dunk Contest Winner: "
            + winner
            + "("
            + winner.getScore()
            + ")"
            + "("
            + winner.getTotalScore()
            + ") --");
  }
}
