import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {

    //    ThreePointContest tpc =
    //        new ThreePointContest(
    //            PlayerList.klay_thompson,
    //            PlayerList.buddy_hield,
    //            PlayerList.kevin_durant,
    //            PlayerList.lebron_james,
    //            PlayerList.glen_rice,
    //            PlayerList.steph_curry);
    //
    //    DunkContest dc =
    //        new DunkContest(
    //            PlayerList.aaron_gordon,
    //            PlayerList.mac_mcclung,
    //            PlayerList.vince_carter,
    //            PlayerList.zach_lavine);
    //
    //    AllStarWeekend asw = new AllStarWeekend(tpc, dc);
    //    asw.simDetailed();

    List<Player> players3 =
        Arrays.asList(
            PlayerList.klay_thompson,
            PlayerList.buddy_hield,
            PlayerList.kevin_durant,
            PlayerList.lebron_james,
            PlayerList.glen_rice,
            PlayerList.steph_curry);

    List<Player> playersD =
        Arrays.asList(
            PlayerList.miles_bridges,
            PlayerList.lebron_james,
            PlayerList.vince_carter,
            PlayerList.mac_mcclung);

    TPC tp = new TPC(players3);

    // tp.simulate_TPC();

    DC dc = new DC(playersD);

    dc.simulate_DC();
  }
}
