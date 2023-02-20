import java.util.Random;
public class Dunk {
    private String name;
    private int difficulty;
    private int dunkScore;
    Random random = new Random();
    int randNum;
    int ranDunk;

    public enum DunkType {
        SAFETY, PRO, ELITE, LEGENDARY
    }

    private DunkType type;

    public Dunk(String n, int d, int s, DunkType t){
        name = n;
        difficulty = d;
        dunkScore = s;
        type = t;
    }
    public String getName(){
        return name;
    }
    public int getDifficulty(){
        return difficulty;
    }
    public int getDunkScore(int attempt){
        randNum = random.nextInt(10) - 5;
        ranDunk = dunkScore + randNum;
        if(ranDunk > 50){ranDunk = 50;}
        if(ranDunk < 38){ranDunk = 38;}


        switch(type) {
            case LEGENDARY:
                if(attempt == 1)
                    return ranDunk;
                else if(attempt == 2)
                    return ranDunk - 1;
                else
                    return ranDunk - 2;
            case ELITE:
                if(attempt == 1)
                    return ranDunk;
                else if(attempt == 2)
                    return ranDunk - 2;
                else
                    return ranDunk - 4;
            case PRO:
                if(attempt == 1)
                    return ranDunk;
                else if(attempt == 2)
                    return (int)(ranDunk * 0.95);
                else
                    return (int)(ranDunk * 0.9);
            case SAFETY:
                if(attempt == 1)
                    return ranDunk;
                else if(attempt == 2)
                    return (int)(ranDunk * 0.9);
                else
                    return (int)(ranDunk * 0.8);
            default:
                return 0;
        }
    }

}
