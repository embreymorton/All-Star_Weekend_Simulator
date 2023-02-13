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

    private Dunk generateDunk(int dunkTier){
        switch(dunkTier){
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

    private boolean madeDunk(Player p, Dunk d){
        rating = p.getDunkRating();
        difficulty = d.getDifficulty();

        randNum = difficulty - (int)(Math.random() * 15);
        //System.out.println(randNum);

        if(rating > randNum) {return true;}
        return false;
    }

    private void simTurn(Player p){
        dunkerType = p.getDunkerType();
        dunk = generateDunk(dunkerType);

        for(int i = 1; i <= 3; i++) {
            if(i == 3){
                dunk = generateDunk(dunkerType - 1);
                if (madeDunk(p, dunk)) {
                    p.addScore(dunk.getDunkScore(i));
                    System.out.println("Attempt " + i + ": Successful " + dunk.getName() + " Dunk");
                } else{
                    System.out.println("Attempt " + i + ": Missed " + dunk.getName() + " Dunk");
                }
                break;
            }

            if (madeDunk(p, dunk)) {
                p.addScore(dunk.getDunkScore(i));
                System.out.println("Attempt " + i + ": Successful " + dunk.getName() + " Dunk");
                break;
            } else
                System.out.println("Attempt " + i + ": Missed " + dunk.getName() + " Dunk");
        }
        System.out.println("Score: " + p.getDunkScore());
    }

    private void simFirstRound(){
        PriorityQueue<Player> updated = new PriorityQueue<>(2, new PlayerComparator());

        System.out.println("First Round: Dunk One");
        for(Player p : players){
            System.out.println(p.getName());
            simTurn(p);
            updated.add(p);
            System.out.println();
        }

        for(Player p : players){
            p.setDunkScore(0);
        }

        System.out.println("First Round: Dunk Two");
        for(Player p : players){
            System.out.println(p.getName());
            simTurn(p);
            System.out.println();
        }

        Player p1;
        Player p2;
        Player pWin;

        for (int i = 1; i <= 2; i++) {
            p1 = updated.poll();
            p2 = updated.peek();

            if((p1.getScore() == p2.getScore()) && (i == 2)){
                pWin = tiebreaker(p1, p2);
                secondRound.add(pWin);
                System.out.println();
                System.out.println(pWin.getName() + " advances");
                System.out.println();
                continue;
            }
            secondRound.add(p1);
        }
    }

    private void simSecondRound(){
        PriorityQueue<Player> updated = new PriorityQueue<>(2, new PlayerComparator());

        for(Player p : secondRound){
            p.setDunkScore(0);
            p.setScore(0);
        }

        System.out.println("Second Round: Dunk One");
        for(Player p : secondRound){
            System.out.println(p.getName());
            simTurn(p);
            updated.add(p);
            System.out.println();
        }

        for(Player p : secondRound){
            p.setDunkScore(0);
        }

        System.out.println("Second Round: Dunk Two");
        for(Player p : secondRound){
            System.out.println(p.getName());
            simTurn(p);
            System.out.println();
        }

        for(Player p : updated){
            final2.add(p);
        }

        Player p1 = final2.poll();
        Player p2 = final2.poll();

        if(p1.getScore() == p2.getScore()) {
            winner = tiebreaker(p1, p2);
            System.out.println();
        } else {
            winner = p1;
            runner_up = p2;
        }

    }

    public void simDunkContest(){
        System.out.println("Dunk Contest:");
        simFirstRound();
        simSecondRound();
        System.out.println("Dunk Contest Winner: " + winner.getName());
        System.out.println("Dunk Contest Runner-Up: " + runner_up.getName());
    }

    private Player tiebreaker(Player p1, Player p2) {
        p1.setDunkScore(0);
        p2.setDunkScore(0);
        System.out.println("Tiebreaker between: " + p1.getName() + " and " + p2.getName() + ":");
        System.out.println(p1.getName());
        simTurn(p1);
        System.out.println();
        System.out.println(p2.getName());
        simTurn(p2);

        if(p1.getDunkScore() > p2.getDunkScore()){
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
