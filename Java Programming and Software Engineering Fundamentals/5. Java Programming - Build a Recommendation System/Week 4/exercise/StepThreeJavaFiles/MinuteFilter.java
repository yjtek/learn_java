package StepThreeJavaFiles;

public class MinuteFilter implements Filter {
    private int myMinMinutes;    
    private int myMaxMinutes;    

    public MinuteFilter(int minMinutes, int maxMinutes) {
        myMinMinutes = minMinutes;
        myMaxMinutes = maxMinutes;
    }

    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= myMinMinutes && MovieDatabase.getMinutes(id) <= myMaxMinutes;
    }

}
