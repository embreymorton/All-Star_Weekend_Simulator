public class DunkList {

    //SAFETY DUNKS
    public static Dunk three_sixty = new Dunk("Basic 360", 75, 35, Dunk.DunkType.SAFETY);

    //PRO Dunks
    public static Dunk windmill = new Dunk("Basic Windmill", 85, 40, Dunk.DunkType.PRO);

    //ELITE Dunks
    public static Dunk three_sixty_windmill = new Dunk("360 Windmill", 100, 48, Dunk.DunkType.PRO);

    //LEGENDARY Dunks
    public static Dunk free_throw_line = new Dunk("Free Throw Line", 110,50, Dunk.DunkType.ELITE);
    public static Dunk windmill_honeydip = new Dunk("Windmill Honeydip", 105, 50, Dunk.DunkType.ELITE);

    //DUNK ARRAYS
    public static Dunk[] safety_dunks = {three_sixty};
    public static Dunk[] pro_dunks = {windmill};
    public static Dunk[] elite_dunks = {three_sixty_windmill};
    public static Dunk[] legendary_dunks = {free_throw_line, windmill_honeydip};


}
