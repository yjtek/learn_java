package exercise2.EarthquakeFilterStarterProgram;

public class PhraseFilter implements Filter {
    private String requestType;
    private String searchPhrase;

    public PhraseFilter(String reqType, String phrase) {
        requestType = reqType;
        searchPhrase = phrase;
    }

    public boolean satisfies(QuakeEntry qe){
        switch (requestType) {
            case "start":
                if (qe.getInfo().startsWith(searchPhrase)) {
                    return true;
                }
            case "end":
                if (qe.getInfo().endsWith(searchPhrase)) {
                    return true;
                }
            case "any":
                if (qe.getInfo().contains(searchPhrase)) {
                    return true;
                }
            default:
                return false;
        }
    }
}
