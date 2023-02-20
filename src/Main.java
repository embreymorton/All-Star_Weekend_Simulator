public class Main {
    public static void main(String[] args) {

        ThreePointContest tpc = new ThreePointContest(
                PlayerList.klay_thompson,
                PlayerList.reggie_miller,
                PlayerList.michael_jordan,
                PlayerList.lebron_james,
                PlayerList.larry_bird,
                PlayerList.steph_curry);

        DunkContest dc = new DunkContest(
                PlayerList.michael_jordan,
                PlayerList.vince_carter,
                PlayerList.lebron_james,
                PlayerList.kobe_bryant);

        AllStarWeekend asw = new AllStarWeekend(tpc, dc);
        asw.simDetailed();

    }
}