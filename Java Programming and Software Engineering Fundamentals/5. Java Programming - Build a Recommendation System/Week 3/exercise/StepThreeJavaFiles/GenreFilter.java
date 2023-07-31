package StepThreeJavaFiles;

public class GenreFilter implements Filter {
    
    private String filterGenre;
    
    public GenreFilter(String genre) {
        filterGenre = genre;
    }

    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(filterGenre);
    }
}
