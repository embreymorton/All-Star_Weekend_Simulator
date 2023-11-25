import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

      List<Player> contestants_TPC = new ArrayList<>();
      List<Player> contestants_DC = new ArrayList<>();

      inputContestants(contestants_TPC, contestants_DC);

      ThreePointContest TPC = new ThreePointContest(contestants_TPC);
      DunkContest DC = new DunkContest(contestants_DC);
      AllStarWeekend ASW = new AllStarWeekend(TPC, DC);

      ASW.sim_weekend();
  }

  private static void inputContestants(List<Player> contestants_TPC, List<Player> contestants_DC){
      Scanner in = new Scanner(System.in);
      String input;

      System.out.println("Select SIX 3 Point Contest Competitors: (Leave line BLANK for a Random Player)");
      while(contestants_TPC.size() < 6){
          input = in.nextLine();
          if(input.equals("")) contestants_TPC.add(PlayerList.getRandomPlayer());
          else if (PlayerList.playerMap.get(input) == null){
              System.out.println("Invalid Player: please enter again");
          } else {
              contestants_TPC.add(PlayerList.playerMap.get(input));
          }
      }

    System.out.println("Select FOUR Dunk Contest Competitors: (Leave line BLANK for a Random Player)");
      while(contestants_DC.size() < 4){
          input = in.nextLine();
          if(input.equals("")) contestants_DC.add(PlayerList.getRandomPlayer());
          else if (PlayerList.playerMap.get(input) == null){
              System.out.println("Invalid Player: please enter again");
          } else {
              contestants_DC.add(PlayerList.playerMap.get(input));
          }
      }
  }
}
