import java.lang.Math;
import java.util.PriorityQueue;

public class ThreePointContest {
    private Player p1, p2, p3, p4, p5, p6, winner;

    private PriorityQueue<Player> players = new PriorityQueue<>(6, new PlayerComparator());
    private PriorityQueue<Player> secondRound = new PriorityQueue<>(3, new PlayerComparator());
    private PriorityQueue<Player> final3 = new PriorityQueue<>(3, new PlayerComparator());

    private int randNum;

    public ThreePointContest(Player one, Player two, Player three, Player four, Player five, Player six) {
        p1 = one;
        p2 = two;
        p3 = three;
        p4 = four;
        p5 = five;
        p6 = six;

        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        players.add(p5);
        players.add(p6);

        for (Player p : players) {
            if (p.getType() == Player.PlayerType.DUNKER) {
                throw new IllegalArgumentException(p.getName() + " cannot participate in the 3PT Contest");
            }
        }
    }

    //Simulates a regular shot based on Player's rating
    private boolean madeShot(Player p, boolean inDepth) {
        randNum = (int) (Math.random() * 125);

        if (inDepth) {
            if (p.getThreeRating() >= randNum) {
                System.out.print("O ");
                return true;
            }
            System.out.print("X ");
            return false;
        }
        if (p.getThreeRating() >= randNum) {
            return true;
        }
        return false;
    }

    //Simulates a money ball shot (worth 2 points)
    private boolean madeMoneyBall(Player p, boolean inDepth) {
        randNum = (int) (Math.random() * 150);

        if (inDepth) {
            if (p.getThreeRating() >= randNum) {
                System.out.print("M ");
                return true;
            }
            System.out.print("X ");
            return false;
        }
        if (p.getThreeRating() >= randNum) {
            return true;
        }
        return false;
    }

    //Simulates a Mountain Dew shot (worth 3 points)
    private boolean madeMountainDew(Player p, boolean inDepth) {
        randNum = (int) (Math.random() * 250);

        if (inDepth) {
            if (p.getThreeRating() >= randNum) {
                System.out.print("3 ");
                return true;
            }
            System.out.print("X ");
            return false;
        }
        if (p.getThreeRating() >= randNum) {
            return true;
        }
        return false;
    }

    //Simulates 5 shots (4 Regular shots = 1 point each, 1 Money Ball shot = 2 points)
    private void simRack(Player p, boolean inDepth) {
        for (int i = 0; i < 4; i++) {
            if (madeShot(p, inDepth)) {
                p.addScore(1);
            }
            ;
        }
        if (madeMoneyBall(p, inDepth)) {
            p.addScore(2);
        }
        ;
    }

    //Simulates Rack with 5 Money Ball Shots
    private void simMoneyBallRack(Player p, boolean inDepth) {
        for (int i = 0; i < 5; i++) {
            if (madeMoneyBall(p, inDepth)) {
                p.addScore(2);
            }
            ;
        }
    }

    //Simulates 5 Racks
    private void simTurn(Player p, boolean inDepth) {
        int mBallRack = (int) (Math.random() * 4 + 1);

        for (int i = 1; i <= 5; i++) {
            if (i == mBallRack) {
                simMoneyBallRack(p, inDepth);
                if (i == 2 || i == 4) {
                    if (madeMountainDew(p, inDepth)) {
                        p.addScore(3);
                    }
                }
                if (inDepth) System.out.println();
                continue;
            }

            simRack(p, inDepth);
            if (i == 2 || i == 4) {
                if (madeMountainDew(p, inDepth)) {
                    p.addScore(3);
                }
            }
            if (inDepth) System.out.println();
        }

        System.out.print("Score:" + p.getScore());
        System.out.println();
    }

    //Simulates a Turn for each of the 6 players --> then puts the top 3 scorers into new Priority Queue
    private void simFirstRound(boolean inDepth) {
        PriorityQueue<Player> updated = new PriorityQueue<>(6, new PlayerComparator());
        for (Player p : players) {
            p.setScore(0);
        }

        if (inDepth) {
            for (Player p : players) {
                System.out.println(p.getName());
                simTurn(p, inDepth);
                updated.add(p);
                System.out.println();
            }
        } else {
            for (Player p : players) {
                System.out.print(p.getName() + " --> ");
                simTurn(p, inDepth);
                updated.add(p);
            }
        }

        Player p1;
        Player p2;
        Player pWin;
        for (int i = 1; i <= 3; i++) {
            p1 = updated.poll();
            p2 = updated.peek();

            if ((p1.getScore() == p2.getScore()) && (i == 3)) {
                if (inDepth) {
                    pWin = tiebreaker(p1, p2, inDepth);
                    players.remove(pWin);
                    secondRound.add(pWin);
                    System.out.println();
                    System.out.println(pWin.getName() + " advances");
                    System.out.println();
                    continue;
                } else {
                    System.out.println();
                    pWin = tiebreaker(p1, p2, inDepth);
                    players.remove(pWin);
                    secondRound.add(pWin);
                    System.out.println(pWin.getName() + " advances");
                    continue;
                }
            }
            secondRound.add(p1);
        }
    }

    //Simulates a Turn for each of the 3 finalists --> inserts them into new Priority Queue to resort
    private void simSecondRound(boolean inDepth) {
        PriorityQueue<Player> updated = new PriorityQueue<>(3, new PlayerComparator());

        for (Player p : secondRound) {
            p.setScore(0);
        }

        if (inDepth) {
            for (Player p : secondRound) {
                System.out.println(p.getName());
                simTurn(p, inDepth);
                updated.add(p);
                System.out.println();
            }
        } else {
            for (Player p : secondRound) {
                System.out.print(p.getName() + " --> ");
                simTurn(p, false);
                updated.add(p);
            }
        }

        Player p;
        for (int i = 0; i < 3; i++) {
            p = updated.poll();
            final3.add(p);
        }

        Player p1 = final3.poll();
        Player p2 = final3.poll();
        Player p3 = final3.poll();

        if (p1.getScore() == p2.getScore() && p1.getScore() == p3.getScore()) {
            System.out.println();
            System.out.println("TieBreaker Round");
            simSecondRound(inDepth);
            return;
        }

        if (p1.getScore() == p2.getScore()) {
            if (!inDepth) System.out.println();
            winner = tiebreaker(p1, p2, inDepth);
            System.out.println();
        } else {
            winner = p1;
            System.out.println();
        }

    }

    //Simulates a 3PT Contest --> inDepth version that shows all makes & misses
    public void sim3PTContest_inDepth() {
        System.out.println("First Round:");
        System.out.println();
        simFirstRound(true);
        System.out.println("Second Round:");
        System.out.println();
        simSecondRound(true);
        System.out.println("3PT Contest Winner: " + winner.getName());
    }

    //Simulates a 3PT Contest --> Simplified version that only shows scores
    public void sim3PTContest_Simple() {
        System.out.println("First Round:");
        simFirstRound(false);
        System.out.println();
        System.out.println("Second Round:");
        simSecondRound(false);
        System.out.println("3PT Contest Winner: " + winner.getName());
    }

    public Player getWinner() {
        return winner;
    }

    //Resolves a tie between two Players
    private Player tiebreaker(Player p1, Player p2, boolean inDepth) {
        int score1 = 0;
        int score2 = 0;
        System.out.println("Tiebreaker between: " + p1.getName() + " and " + p2.getName() + ":");

        System.out.println(p1.getName());
        for (int i = 1; i <= 15; i++) {
            if (i % 5 == 0) {
                if (madeMoneyBall(p1, inDepth)) {
                    score1 += 2;
                }
                if(i == 15){
                    if(madeMountainDew(p1, inDepth)) {score1 += 3;}
                }
                if (inDepth) System.out.println();
                continue;
            }
            if (madeShot(p1, inDepth)) {
                score1 += 1;
            }
        }
        System.out.println("Score: " + score1);

        if (inDepth) System.out.println();

        System.out.println(p2.getName());
        for (int i = 1; i <= 15; i++) {
            if (i % 5 == 0) {
                if (madeMoneyBall(p2, inDepth)) {
                    score2 += 2;
                }
                if(i == 15){
                    if(madeMountainDew(p1, inDepth)) {score2 += 3;}
                }
                if (inDepth) System.out.println();
                continue;
            }
            if (madeShot(p2, inDepth)) {
                score2 += 1;
            }
        }
        System.out.println("Score: " + score2);

        if (score1 > score2) return p1;
        else if (score2 > score1) return p2;
        else {
            System.out.println();
            return tiebreaker(p1, p2, inDepth);
        }

    }


}
