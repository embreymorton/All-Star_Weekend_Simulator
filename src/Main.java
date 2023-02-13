public class Main {
    public static void main(String[] args) {

        ThreePointContest tp = new ThreePointContest(
                PlayerList.klay_thompson,
                PlayerList.kevin_durant,
                PlayerList.kyrie_irving,
                PlayerList.devin_booker,
                PlayerList.lamelo_ball,
                PlayerList.steph_curry);

        tp.sim3PTContest_inDepth();

        DunkContest dc = new DunkContest(
                PlayerList.zach_lavine,
                PlayerList.ja_morant,
                PlayerList.miles_bridges,
                PlayerList.zion_williamson);

        dc.simDunkContest();
    }
}