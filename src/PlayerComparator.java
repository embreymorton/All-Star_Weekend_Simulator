import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PlayerComparator implements Comparator<Player> {

  public int compare(Player p1, Player p2) {
    if (p1.getScore() < p2.getScore()) return 1;
    else if (p1.getScore() > p2.getScore()) return -1;
    return 0;
  }

  public Player compare(Player p1, Player p2, Player p3) {
    List<Player> list = new ArrayList<>(Arrays.asList(p1, p2, p3));
    list.sort(this);

    if (list.get(0).getScore() == list.get(1).getScore()) return null;
    return list.get(0);
  }
}
