import java.util.PriorityQueue;
import java.util.Random;

public class DunkContest {
    private Player p1, p2, p3, p4, winner, runner_up;
    private PriorityQueue<Player> players = new PriorityQueue<>(4, new PlayerComparator());
    private PriorityQueue<Player> secondRound = new PriorityQueue<>(2, new PlayerComparator());
    private PriorityQueue<Player> final2 = new PriorityQueue<>(2, new PlayerComparator());
    private int randNum;
    private int rating;
    private int difficulty;
    private Dunk dunk;
    private Dunk[] legendaryDunks = DunkList.legendary_dunks.clone();
    private Dunk[] eliteDunks = DunkList.elite_dunks.clone();
    private Dunk[] proDunks = DunkList.pro_dunks.clone();
    private Dunk[] safetyDunks = DunkList.safety_dunks.clone();
    private Random generator = new Random();
    int random;
    int dunkerType;


    public DunkContest(Player one, Player two, Player three, Player four) {
        p1 = one;
        p2 = two;
        p3 = three;
        p4 = four;

        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        for (Player p : players) {
            if (p.getType() == Player.PlayerType.SHOOTER) {
                throw new IllegalArgumentException(p.getName() + " cannot participate in the Dunk Contest");
            }
        }
    }

    public Player getDunkWinner() {
        return winner;
    }

    public void getContestants() {
        System.out.println("Dunk Contest Contestants: ");
        int i = 1;
        for (Player p : players) {
            if (i == 4) System.out.print(p.getName());
            else System.out.print(p.getName() + ", ");
            i++;
        }
        System.out.println();
    }

    //Randomly generates a Dunk from the Array of Dunks based on DunkTier/Level
    private Dunk generateDunk(int dunkTier) {
        switch (dunkTier) {
            case 4:
                random = new Random().nextInt(legendaryDunks.length);
                return legendaryDunks[random];
            case 3:
                random = new Random().nextInt(eliteDunks.length);
                return eliteDunks[random];
            case 2:
                random = new Random().nextInt(proDunks.length);
                return proDunks[random];
            default:
                random = new Random().nextInt(safetyDunks.length);
                return safetyDunks[random];
        }
    }

    //Randomly calculates if a Dunk is made or missed
    private boolean madeDunk(Player p, Dunk d) {
        rating = p.getDunkRating();
        difficulty = d.getDifficulty();

        randNum = difficulty - (int) (Math.random() * 15);

        if (rating > randNum) {
            return true;
        }
        return false;
    }

    //Simulates a Dunk Attempt for a Player (3 Attempts)
    //Score diminishes with each attempt
    private void simTurn(Player p, boolean inDepth) {
        dunkerType = p.getDunkerType();
        dunk = generateDunk(dunkerType);

        for (int i = 1; i <= 3; i++) {
            if (i == 3) {
                dunk = generateDunk(dunkerType - 1);
                if (madeDunk(p, dunk)) {
                    p.addScore(dunk.getDunkScore(i));
                    if (inDepth) System.out.println("Attempt " + i + ": Successful " + dunk.getName() + " Dunk");
                    else {
                        System.out.print(p.getName() + ": Successful " + dunk.getName() + " Dunk --> ");
                        System.out.println("Score: " + p.getDunkScore());
                    }
                } else {
                    if (i == 3 || inDepth) {
                        if (inDepth) System.out.println("Attempt " + i + ": Missed " + dunk.getName() + " Dunk");
                        else {
                            System.out.print(p.getName() + ": Failed to Complete " + dunk.getName() + " Dunk --> ");
                            p.addScore(30);
                            System.out.println("Score: " + p.getDunkScore());
                        }
                    }
                }
                break;
            }

            if (madeDunk(p, dunk)) {
                p.addScore(dunk.getDunkScore(i));
                if (inDepth) System.out.println("Attempt " + i + ": Successful " + dunk.getName() + " Dunk");
                else {
                    System.out.print(p.getName() + ": Successful " + dunk.getName() + " Dunk --> ");
                    System.out.println("Score: " + p.getDunkScore());
                }
                break;
            } else if (i == 3 || inDepth) {
                if (inDepth) System.out.println("Attempt " + i + ": Missed " + dunk.getName() + " Dunk");
                else {
                    System.out.print(p.getName() + ": Missed " + dunk.getName() + " Dunk --> ");
                    p.addScore(30);
                    System.out.println("Score: " + p.getDunkScore());
                }
            }
        }
        if (inDepth && (p.getDunkScore() == 0)) {
            p.addScore(30);
        }
        if (inDepth) System.out.println("Score: " + p.getDunkScore());
    }

    //Simulates 2 Dunks for each of the 4 contestants
    //Places 2 highest scorers into Second Round priority queue
    private void simFirstRound(boolean inDepth) {
        PriorityQueue<Player> updated = new PriorityQueue<>(2, new PlayerComparator());

        for (Player p : players) {
            p.setDunkScore(0);
            p.setScore(0);
        }

        System.out.println("First Round: Dunk One");
        for (Player p : players) {
            if (inDepth) System.out.println(p.getName());
            simTurn(p, inDepth);
            if (inDepth) System.out.println();
        }

        for (Player p : players) {
            p.setDunkScore(0);
        }

        if (!inDepth) System.out.println();

        System.out.println("First Round: Dunk Two");
        for (Player p : players) {
            if (inDepth) System.out.println(p.getName());
            simTurn(p, inDepth);
            updated.add(p);
            if (inDepth) System.out.println();
        }

        Player p1;
        Player p2;
        Player pWin;

        for (int i = 1; i <= 2; i++) {
            p1 = updated.poll();
            p2 = updated.peek();

            if ((p1.getScore() == p2.getScore()) && (i == 2)) {
                if (!inDepth) System.out.println();
                pWin = tiebreaker(p1, p2, inDepth);
                secondRound.add(pWin);
                if (inDepth) System.out.println();
                System.out.println(pWin.getName() + " advances");
                if (inDepth) System.out.println();
                continue;
            }
            secondRound.add(p1);
        }

        if (inDepth) {
            p1 = secondRound.poll();
            p2 = secondRound.poll();
            System.out.println("Advancing to Final Round: ");
            System.out.println(p1.getName() + "(" + p1.getScore() + ")");
            System.out.println(p2.getName() + "(" + p2.getScore() + ")");
            secondRound.add(p1);
            secondRound.add(p2);
        }

    }

    //Simulates 2 Dunks for each of the finalists
    private void simSecondRound(boolean inDepth) {
        PriorityQueue<Player> updated = new PriorityQueue<>(2, new PlayerComparator());

        for (Player p : secondRound) {
            p.setDunkScore(0);
            p.setScore(0);
        }

        System.out.println("Second Round: Dunk One");
        for (Player p : secondRound) {
            if (inDepth) System.out.println(p.getName());
            simTurn(p, inDepth);
            updated.add(p);
            if (inDepth) System.out.println();
        }

        for (Player p : secondRound) {
            p.setDunkScore(0);
        }

        if (!inDepth) System.out.println();

        System.out.println("Second Round: Dunk Two");
        for (Player p : secondRound) {
            if (inDepth) System.out.println(p.getName());
            simTurn(p, inDepth);
            if (inDepth) System.out.println();
        }

        for (Player p : updated) {
            final2.add(p);
        }

        Player p1 = final2.poll();
        Player p2 = final2.poll();

        if (p1.getScore() == p2.getScore()) {
            if (!inDepth) System.out.println();
            winner = tiebreaker(p1, p2, inDepth);
            if (inDepth) System.out.println();
        } else {
            winner = p1;
            runner_up = p2;
        }

    }

    //Simulates a Dunk Contest that shows each Dunk attempt in detail
    public void simDunkContest_inDepth() {
        simFirstRound(true);
        System.out.println();
        simSecondRound(true);
        System.out.println("Dunk Contest Winner: " + winner.getName() + "(" + winner.getScore() + ")");
        System.out.println("Dunk Contest Runner-Up: " + runner_up.getName() + "(" + runner_up.getScore() + ")");
    }

    //Simulates a Dunk Contest that only shows one dunk and the score that corresponds with it
    public void simDunkContest_simple() {
        simFirstRound(false);
        System.out.println();
        simSecondRound(false);
        System.out.println();
        System.out.println("Dunk Contest Winner: " + winner.getName() + "(" + winner.getScore() + ")");
        System.out.println("Dunk Contest Runner-Up: " + runner_up.getName() + "(" + runner_up.getScore() + ")");
    }

    //Resolves a tie between two Players
    private Player tiebreaker(Player p1, Player p2, boolean inDepth) {
        p1.setDunkScore(0);
        p2.setDunkScore(0);
        System.out.println("Tiebreaker between: " + p1.getName() + " and " + p2.getName() + ":");
        if (inDepth) System.out.println(p1.getName());
        simTurn(p1, inDepth);
        if (inDepth) System.out.println();
        if (inDepth) System.out.println(p2.getName());
        simTurn(p2, inDepth);

        if (p1.getDunkScore() == p2.getDunkScore()) {
            System.out.println();
            p1.addScore(p1.getDunkScore() * -1);
            p2.addScore(p2.getDunkScore() * -1);
            return tiebreaker(p1, p2, inDepth);
        }

        if (p1.getDunkScore() > p2.getDunkScore()) {
            p1.addScore(p1.getDunkScore() * -1);
            p2.addScore(p2.getDunkScore() * -1);
            runner_up = p2;
            return p1;
        }
        p1.addScore(p1.getDunkScore() * -1);
        p2.addScore(p2.getDunkScore() * -1);
        runner_up = p1;
        return p2;
    }


}
