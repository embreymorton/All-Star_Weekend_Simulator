public class AllStarWeekend {

    private final DC dc;
    private final TPC tpc;
    private final Player[] winners = new Player[2];

    public AllStarWeekend(TPC tpc, DC dc) {
      this.dc = dc;
      this.tpc = tpc;
    }

    private void congratulateWinners() {
      winners[0] = tpc.getWinner();
      winners[1] = dc.getWinner();

      System.out.println(
          "Congratulations to " + winners[0].getName() + " and " + winners[1].getName() + "!");
    }

    // Simulates an All-Star Weekend with a Detailed 3PT and Dunk Contest
    public void sim_weekend() {
      System.out.println("Welcome to All-Star Saturday Night!");
      System.out.println();
      tpc.simulate_TPC();
      System.out.println();
      dc.simulate_DC();
      System.out.println();
      System.out.println("That concludes our All-Star Events.");
      congratulateWinners();
    }

}
