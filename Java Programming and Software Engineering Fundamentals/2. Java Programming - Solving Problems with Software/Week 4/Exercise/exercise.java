import edu.duke.*;
import org.apache.commons.csv.*;

public class exercise {
	public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int totalUniqueBoys = 0;
		int totalUniqueGirls = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				totalUniqueBoys++;
			}
			else {
				totalGirls += numBorn;
				totalUniqueGirls++;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("female births = " + totalGirls);
		System.out.println("male births = " + totalBoys);
		System.out.println("unique female names = " + totalUniqueBoys);
		System.out.println("unique male names = " + totalUniqueGirls);

	}

	public int getRank(int year, String name, String gender){
		FileResource fr = new FileResource("data/us_babynames_by_year/yob"+year+".csv");
		CSVParser parser = fr.getCSVParser();
		int rank = 1;
		for (CSVRecord r: parser){
			if (!r.get(1).equals(gender)) {
				continue;
			}
			else if ((r.get(1).equals(gender)) && (r.get(0).equals(name))){
				return rank;
			}
			else {
				rank++;
			}
		}
		return -1;
	}

	public String getName(Integer year, Integer rank, String gender){
		FileResource fr = new FileResource("data/us_babynames_by_year/yob"+year+".csv");
		CSVParser parser = fr.getCSVParser();
		Integer iterrank = 1;
		for (CSVRecord r: parser){
			if (!r.get(1).equals(gender)){
				continue;
			}
			else if (iterrank.equals(rank)) {
				return r.get(0);
			}
			else {
				iterrank++;
			}
		}
		return "NO NAME";
	}

	public void whatIsNameInYear(String name, Integer year, Integer newYear, String gender){
		int name_rank = getRank(year, name, gender);
		System.out.println(getName(year, name_rank, gender));
	}

	public void testTotalBirths () {
		//FileResource fr = new FileResource();
		FileResource fr = new FileResource("data/us_babynames_test/yob2014short.csv");
		totalBirths(fr);
	}

	public void testGetRank(){
		System.out.println(getRank(2012, "Mason", "F"));
	}

	public void testGetName(){
		System.out.println(getName(2014, 1, "M"));
	}

	public void testWhatIsNameInYear(){
		whatIsNameInYear("Sophia", 2012, 2014, "F");
	}

	public static void main(String[] args){
		exercise e = new exercise();
		// e.testTotalBirths();
		// e.testGetRank();
		// e.testGetName();
		e.testWhatIsNameInYear();
	}
}

