import java.util.Random;
public class Main {
    public static void main(String[] args) {

//        ThreePointContest tp = new ThreePointContest(
//                PlayerList.lebron_james,
//                PlayerList.kevin_durant,
//                PlayerList.kyrie_irving,
//                PlayerList.devin_booker,
//                PlayerList.lamelo_ball,
//                PlayerList.steph_curry);
//
//        tp.sim3PTContest_inDepth();

        DunkContest dc = new DunkContest(
                PlayerList.dennis_smith,
                PlayerList.mason_plumlee,
                PlayerList.miles_bridges,
                PlayerList.zion_williamson);

        dc.simDunkContest();
    }
}