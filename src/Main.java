import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

      List<Player> contestants_TPC = new ArrayList<>();
      inputContestants(contestants_TPC, 6, "3 Point Contest");
      List<Player> contestants_DC = new ArrayList<>();
      inputContestants(contestants_DC, 4, "Dunk Contest");

      ThreePointContest TPC = new ThreePointContest(contestants_TPC);
      DunkContest DC = new DunkContest(contestants_DC);
      AllStarWeekend ASW = new AllStarWeekend(TPC, DC);

      ASW.sim_weekend();
  }

    private static void inputContestants(List<Player> contestants, int numberOfContestants, String contestName) {
        Scanner in = new Scanner(System.in);
        String input;
        Player current;

        System.out.println("Select " + numberOfContestants + " " + contestName + " Competitors: (Leave line BLANK for a Random Player)");

        while (contestants.size() < numberOfContestants) {
            input = in.nextLine();

            if (input.equals("")) {
                current = PlayerList.getRandomPlayer();
                while (contestants.contains(current)) {
                    current = PlayerList.getRandomPlayer();
                }
                contestants.add(current);
            } else {
                Player selectedPlayer = PlayerList.playerMap.get(input);
                if (selectedPlayer == null) {
                    System.out.println("Invalid Player: please choose again");
                } else if (contestants.contains(selectedPlayer)) {
                    System.out.println("Duplicate Player: please choose again");
                } else {
                    contestants.add(selectedPlayer);
                }
            }
        }
    }
}
