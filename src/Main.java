

public class Main {
    public static void main(String[] args) {

        ThreePointContest tp = new ThreePointContest(
                PlayerList.lebron_james,
                PlayerList.kevin_durant,
                PlayerList.kyrie_irving,
                PlayerList.devin_booker,
                PlayerList.lamelo_ball,
                PlayerList.lauri_markkanen);

        tp.sim3PTContest_Simple();
    }
}