public class DunkList {

    //SAFETY DUNKS
    public static final Dunk three_sixty = new Dunk("Basic 360", 75, 35, Dunk.DunkType.SAFETY);

    //PRO Dunks
    public static final Dunk windmill = new Dunk("Basic Windmill", 85, 40, Dunk.DunkType.PRO);

    //ELITE Dunks
    public static final Dunk three_sixty_windmill = new Dunk("360 Windmill", 100, 48, Dunk.DunkType.ELITE);

    //LEGENDARY Dunks
    public static final Dunk free_throw_line = new Dunk("Free Throw Line", 110,50, Dunk.DunkType.LEGENDARY);
    public static final Dunk windmill_honeydip = new Dunk("Windmill Honey-Dip", 105, 50, Dunk.DunkType.LEGENDARY);

    //DUNK ARRAYS
    public static final Dunk[] safety_dunks = {three_sixty};
    public static final Dunk[] pro_dunks = {windmill};
    public static final Dunk[] elite_dunks = {three_sixty_windmill};
    public static final Dunk[] legendary_dunks = {free_throw_line, windmill_honeydip};


}
