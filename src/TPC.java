import java.util.*;

public class TPC {

  private List<Player> contestants;
  private Map<String, Shot> shotTypes;
  private Shot current_shot;
  private Player winner;

  public TPC(List<Player> players) {
    contestants = new ArrayList<>(players);

    shotTypes = new HashMap<>();
    shotTypes.put("Regular", Shot.regular);
    shotTypes.put("Money Ball", Shot.moneyBall);
    shotTypes.put("Mountain Dew", Shot.mountainDew);
  }

  private void printContestants() {
    System.out.print("Contestants: ");
    for(Player p : contestants){
      System.out.print(p);
      if(contestants.indexOf(p) != contestants.size() - 1)
        System.out.print(" -- ");
    }
    System.out.println();
  }

  public Player getWinner(){
    return winner;
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
    player.setScore(0);

    int MB_rack = (int) (Math.random() * 4 + 1);
    for (int i = 0; i < num_racks; i++) {
      if (i == MB_rack) sim_rack(player, "Money Ball");
      else sim_rack(player, "Regular");

      if (i == 1 || i == 3) shotTypes.get("Mountain Dew").takeShot(player);
      System.out.println();
    }
    System.out.println(player.getScore() + "\n");
  }

  private void first_round() {
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
    System.out.println("-------------------------");
    System.out.println("Simulating Tie-Breaker Between " + p1 + " and " + p2);
    System.out.println("-------------------------");

    sim_turn(p1, 3);
    sim_turn(p2, 3);

    PlayerComparator c = new PlayerComparator();
    int result = c.compare(p1, p2);

    if (result == -1) return p1;
    else if (result == 1) return p2;
    else return tie_breaker(p1, p2);
  }

  private List<Player> tie_breaker(Player p1, Player p2, Player p3) {
    System.out.println("-------------------------");
    System.out.println("Simulating Tie-Breaker Between" + p1 + ", " + p2 + " and " + p3);
    System.out.println("-------------------------");
    sim_turn(p1, 3);
    sim_turn(p2, 3);
    sim_turn(p3, 3);

    PlayerComparator c = new PlayerComparator();
    List<Player> result = c.compare(p1, p2, p3);

    if (result == null) return tie_breaker(p1, p2, p3);
    else return result;
  }

  public void simulate_TPC() {
    System.out.println("Three Point Contest Simulation");
    printContestants();
    System.out.println("-------------------------");
    System.out.println("Simulating First Round");
    System.out.println("-------------------------");
    first_round();
    System.out.println("-------------------------");
    System.out.println("Simulating Second Round");
    System.out.println("-------------------------");
    second_round();
    System.out.println("3 Point Contest Winner: " + winner);
  }
}
