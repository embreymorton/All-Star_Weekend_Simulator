public class Main {
    public static void main(String[] args) {
        
        ThreePointContest tpc = new ThreePointContest(
                PlayerList.klay_thompson,
                PlayerList.buddy_hield,
                PlayerList.kevin_durant,
                PlayerList.lebron_james,
                PlayerList.glen_rice,
                PlayerList.steph_curry);

        DunkContest dc = new DunkContest(
                PlayerList.aaron_gordon,
                PlayerList.mac_mcclung,
                PlayerList.vince_carter,
                PlayerList.zach_lavine);

        AllStarWeekend asw = new AllStarWeekend(tpc, dc);
        asw.simDetailed();

    }
}