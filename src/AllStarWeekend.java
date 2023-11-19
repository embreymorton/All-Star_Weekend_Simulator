public class AllStarWeekend {

  private final DunkContest dc;
  private final ThreePointContest tpc;
  private final Player[] winners = new Player[2];

  public AllStarWeekend(ThreePointContest tpc, DunkContest dc) {
    this.dc = dc;
    this.tpc = tpc;
  }

  private void congratulateWinners() {
    winners[0] = tpc.get3PTWinner();
    winners[1] = dc.getDunkWinner();

    System.out.println(
        "Congratulations to " + winners[0].getName() + " and " + winners[1].getName() + "!");
  }

  // Simulates an All-Star Weekend with a Detailed 3PT and Dunk Contest
  public void simDetailed() {
    System.out.println("Welcome to All-Star Saturday Night!");
    System.out.println();
    tpc.getContestants();
    System.out.println();
    tpc.sim3PTContest_inDepth();
    System.out.println();
    dc.getContestants();
    System.out.println();
    dc.simDunkContest_inDepth();
    System.out.println();
    System.out.println("That concludes our All-Star Events.");
    congratulateWinners();
  }

  // Simulates an All-Star Weekend with a Simplified 3PT and Dunk Contest
  public void simSimple() {
    System.out.println("Welcome to All-Star Saturday Night!");
    System.out.println();
    tpc.getContestants();
    System.out.println();
    tpc.sim3PTContest_Simple();
    System.out.println();
    dc.getContestants();
    System.out.println();
    dc.simDunkContest_simple();
    System.out.println();
    congratulateWinners();
  }
}
