package exercise2.EarthquakeFilterStarterProgram;

import java.util.ArrayList;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> listOfFilters;

    public MatchAllFilter() {
        listOfFilters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f) {
        listOfFilters.add(f);
    }

    public boolean satisfies(QuakeEntry qe){
        for (Filter f: listOfFilters){
            if (!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }

    public String getName(){
        String allFilterNames = "";

        for (Filter f: listOfFilters){
            allFilterNames += (" " + f.getName());
        }

        return allFilterNames;
    }

}

