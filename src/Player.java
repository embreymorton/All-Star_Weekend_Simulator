
public class Player {
    String name;
    private int dunkRating;
    private int threeRating;
    private int score = 0;

    public enum PlayerType {
        SHOOTER, DUNKER, BOTH;
    }
    private PlayerType type;

    public Player (String name, int rating, PlayerType type){
        this.name = name;

        switch(type) {
            case SHOOTER:
                threeRating = rating;
                this.type = PlayerType.SHOOTER;
                break;
            case DUNKER:
                dunkRating = rating;
                this.type = PlayerType.DUNKER;
                break;
        }
    }

    public Player (String name, int threeRating, int dunkRating){
        this.name = name;
        this.dunkRating = dunkRating;
        this.threeRating = threeRating;

        type = PlayerType.BOTH;
    }

    public String getName(){return name;}

    public int getDunkRating(){
        return dunkRating;
    }

    public int getThreeRating(){
        return threeRating;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int s){
        score = s;
    }

    public void addScore(int score){
        this.score += score;
    }

    public PlayerType getType(){
        return type;
    }


    public String toString(){
        return name;
    }

}
