public class Main {
    public static void main(String[] args) {

        ThreePointContest tpc = new ThreePointContest(
                PlayerList.klay_thompson,
                PlayerList.kevin_durant,
                PlayerList.kyrie_irving,
                PlayerList.zach_lavine,
                PlayerList.lamelo_ball,
                PlayerList.steph_curry);

        DunkContest dc = new DunkContest(
                PlayerList.zach_lavine,
                PlayerList.ja_morant,
                PlayerList.mac_mcclung,
                PlayerList.zion_williamson);

        AllStarWeekend asw = new AllStarWeekend(tpc, dc);
        asw.simSimple();
    }
}