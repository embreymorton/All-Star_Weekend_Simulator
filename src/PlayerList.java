import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PlayerList {

  //Eventually could store player attributes in separate file

  public static Map<String, Player> playerMap;

  static {
    playerMap = new HashMap<>();

    // CURRENT PLAYERS - SHOOTERS
    playerMap.put("Stephen Curry", new Player("Stephen Curry", 110, 35));
    playerMap.put("Klay Thompson", new Player("Klay Thompson", 100, 50));
    playerMap.put("Buddy Hield", new Player("Buddy Hield", 95, 25));
    playerMap.put("Kevin Durant", new Player("Kevin Durant", 93, 80));
    playerMap.put("Devin Booker", new Player("Devin Booker", 91, 70));
    playerMap.put("Luke Kennard", new Player("Luke Kennard", 91, 25));
    playerMap.put("Joe Harris", new Player("Joe Harris", 90, 25));
    playerMap.put("Karl-Anthony Towns", new Player("Karl-Anthony Towns", 90, 70));
    playerMap.put("Lauri Markkanen", new Player("Lauri Markkanen", 89, 70));
    playerMap.put("Kyrie Irving", new Player("Kyrie Irving", 88, 50));
    playerMap.put("LaMelo Ball", new Player("LaMelo Ball", 87, 50));

    // CURRENT PLAYERS - DUNKERS
    playerMap.put("Mac McClung", new Player("Mac McClung", 60, 102));
    playerMap.put("Aaron Gordon", new Player("Aaron Gordon", 60, 100));
    playerMap.put("Zion Williamson", new Player("Zion Williamson", 60, 99));
    playerMap.put("Ja Morant", new Player("Ja Morant", 70, 97));
    playerMap.put("Derrick Jones Jr.", new Player("Derrick Jones Jr.", 60, 96));
    playerMap.put("Kai Jones", new Player("Kai Jones", 50, 95));
    playerMap.put("Miles Bridges", new Player("Miles Bridges", 75, 95));
    playerMap.put("Dennis Smith Jr.", new Player("Dennis Smith Jr.", 65, 94));
    playerMap.put("Obi Toppin", new Player("Obi Toppin", 65, 90));
    playerMap.put("Ben Simmons", new Player("Ben Simmons", 25, 80));

    // CURRENT PLAYERS - SHOOTERS & DUNKERS
    playerMap.put("Damian Lillard", new Player("Damian Lillard", 96, 80));
    playerMap.put("Zach LaVine", new Player("Zach LaVine", 88, 105));
    playerMap.put("Donovan Mitchell", new Player("Donovan Mitchell", 85, 92));
    playerMap.put("Shaedon Sharpe", new Player("Shaedon Sharpe", 75, 92));
    playerMap.put("Mason Plumlee", new Player("Mason Plumlee", 70, 85));

    // HISTORIC PLAYERS - SHOOTERS
    playerMap.put("Reggie Miller", new Player("Reggie Miller", 100, 65));
    playerMap.put("Larry Bird", new Player("Larry Bird", 98, 50));
    playerMap.put("Peja Stojakovic", new Player("Peja Stojakovic", 94, 30));
    playerMap.put("Glen Rice", new Player("Glen Rice", 94, 70));
    playerMap.put("Steve Kerr", new Player("Steve Kerr", 93, 0));
    playerMap.put("Dirk Nowitzki", new Player("Dirk Nowitzki", 92, 40));

    // HISTORIC PLAYERS - DUNKERS
    playerMap.put("Vince Carter", new Player("Vince Carter", 80, 110));
    playerMap.put("Julius Erving", new Player("Julius Erving", 50, 108));
    playerMap.put("Dominique Wilkins", new Player("Dominique Wilkins", 55, 105));
    playerMap.put("Blake Griffin", new Player("Blake Griffin", 60, 100));

    // HISTORIC PLAYERS - SHOOTERS & DUNKERS
    playerMap.put("LeBron James", new Player("LeBron James", 90, 100));
    playerMap.put("Michael Jordan", new Player("Michael Jordan", 80, 110));
    playerMap.put("Kobe Bryant", new Player("Kobe Bryant", 85, 105));
    playerMap.put("Tracy McGrady", new Player("Tracy McGrady", 85, 97));
    playerMap.put("Ray Allen", new Player("Ray Allen", 100, 85));
  }

  public static Player getRandomPlayer(){
    Random random = new Random();
    Object[] keys = playerMap.keySet().toArray();
    return playerMap.get(keys[random.nextInt(keys.length)]);
  }
}
