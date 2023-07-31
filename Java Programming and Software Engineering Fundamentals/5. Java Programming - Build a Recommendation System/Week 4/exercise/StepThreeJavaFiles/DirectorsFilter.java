package StepThreeJavaFiles;

public class DirectorsFilter implements Filter {
    private String myDirectors;

    public DirectorsFilter(String directors) {
        myDirectors = directors;
    }

    public boolean satisfies(String id) {
        String directors = MovieDatabase.getDirector(id);
        
        String[] queryDirectors = directors.split(",");
        String[] filterDirectors = myDirectors.split(",");

        for (String fd: filterDirectors) {
            for (String qd: queryDirectors) {
                // System.out.println(fd);
                // System.out.println(qd);
                if (qd.equals(fd)) {
                    return true;
                }
            }
        }

        return false;
    }
}
