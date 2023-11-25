import java.util.*;

public class ThreePointContest {
  private List<Player> contestants;
  private Map<String, Shot> shotTypes;
  private Shot current_shot;
  private Player winner;

  public ThreePointContest(List<Player> players) {
    contestants = new ArrayList<>(players);

    shotTypes = new HashMap<>();
    shotTypes.put("Regular", Shot.regular);
    shotTypes.put("Money Ball", Shot.moneyBall);
    shotTypes.put("Mountain Dew", Shot.mountainDew);
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

  private void sim_rack(Player player, String type) {
    for (int i = 0; i < 5; i++) {
      if (i == 4) current_shot = shotTypes.get("Money Ball");
      else current_shot = shotTypes.get(type);
      current_shot.takeShot(player);
    }
  }

  private void sim_turn(Player player, int num_racks) {
    System.out.println(player);

    int MB_rack = (int) (Math.random() * (num_racks - 1) + 1);
    for (int i = 1; i <= num_racks; i++) {
      if (i == MB_rack) sim_rack(player, "Money Ball");
      else sim_rack(player, "Regular");

      if (i == 2 || i == 4) shotTypes.get("Mountain Dew").takeShot(player);
      System.out.println();
    }
    System.out.println(player.getScore() + "\n");
  }

  private void first_round() {
    resetScores(true);
    for (Player p : contestants) {
      sim_turn(p, 5);
    }
    Collections.sort(contestants, new PlayerComparator());

    if (contestants.get(2).getScore() == contestants.get(3).getScore()) {
      contestants.set(2, tie_breaker(contestants.get(2), contestants.get(3)));
    }

    contestants = contestants.subList(0, 3);
  }

  private void second_round() {
    resetScores(false);
    for (Player p : contestants) {
      sim_turn(p, 5);
    }
    Collections.sort(contestants, new PlayerComparator());

    if (contestants.get(0).getScore() == contestants.get(1).getScore()) {
      if (contestants.get(0).getScore() == contestants.get(2).getScore())
        contestants = tie_breaker(contestants.get(0), contestants.get(1), contestants.get(2));
      else contestants.set(0, tie_breaker(contestants.get(0), contestants.get(1)));
    }

    winner = contestants.get(0);
  }

  private Player tie_breaker(Player p1, Player p2) {
    Map<Player, Integer> original_scores = new HashMap<>();
    original_scores.put(p1, p1.getScore());
    original_scores.put(p2, p2.getScore());

    p1.setScore(0);
    p2.setScore(0);
    System.out.println("-------------------------");
    System.out.println("Simulating Tie-Breaker Between " + p1 + " and " + p2);
    System.out.println("-------------------------");

    sim_turn(p1, 3);
    sim_turn(p2, 3);

    PlayerComparator c = new PlayerComparator();
    int result = c.compare(p1, p2);

    if (result == -1){
      p1.setScore(original_scores.get(p1));
      return p1;
    }
    else if (result == 1) {
      p2.setScore(original_scores.get(p2));
      return p2;
    }
    else return tie_breaker(p1, p2);
  }

  private List<Player> tie_breaker(Player p1, Player p2, Player p3) {
    Map<Player, Integer> original_scores = new HashMap<>();
    original_scores.put(p1, p1.getScore());
    original_scores.put(p2, p2.getScore());
    original_scores.put(p3, p3.getScore());

    p1.setScore(0);
    p2.setScore(0);
    p3.setScore(0);

    System.out.println("-------------------------");
    System.out.println("Simulating Tie-Breaker Between" + p1 + ", " + p2 + " and " + p3);
    System.out.println("-------------------------");
    sim_turn(p1, 3);
    sim_turn(p2, 3);
    sim_turn(p3, 3);

    PlayerComparator c = new PlayerComparator();
    List<Player> result = c.compare(p1, p2, p3);

    if (result == null) return tie_breaker(p1, p2, p3);
    else {
      for(Player p : result){
        p.setScore(original_scores.get(p));
      }
      return result;
    }
  }

  public void simulate_TPC() {

    System.out.println("Three Point Contest Simulation");
    printContestants(1);
    System.out.println("-------------------------");
    System.out.println("Simulating First Round");
    System.out.println("-------------------------");
    first_round();
    System.out.println("-------------------------");
    System.out.println("Simulating Second Round");
    printContestants(2);
    System.out.println("-------------------------");
    second_round();
    System.out.println(
        "-- 3 Point Contest Winner: "
            + winner
            + "("
            + winner.getScore()
            + ")"
            + "("
            + winner.getTotalScore()
            + ") --");
  }
}
