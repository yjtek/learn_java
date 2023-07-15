import edu.duke.*;

import java.io.File;

import javax.annotation.processing.Filer;

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

	public Integer getRank(Integer year, String name, String gender){
		FileResource fr = new FileResource("data/us_babynames_by_year/yob"+year+".csv");
		CSVParser parser = fr.getCSVParser(false);
		Integer rank = 1;
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
		CSVParser parser = fr.getCSVParser(false);
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
		System.out.println(name + " born in " + year + " would have been " + getName(newYear, name_rank, gender) + " if she was born in " + newYear);
	}

	public Integer yearOfHighestRank(String name, String gender){
		DirectoryResource dr = new DirectoryResource();
		Integer maxRank = -1;
		Integer maxRankYear = -1;
		for (java.io.File f: dr.selectedFiles()){
			Integer fileYear = Integer.parseInt(f.getName().replaceAll("[^0-9]", ""));
			Integer rank = getRank(fileYear, name, gender);
			if (maxRank.equals(-1) && rank.equals(-1)){
				continue;
			} else if (maxRank.equals(-1) && !rank.equals(-1)){
				maxRankYear = fileYear;
				maxRank = rank;
			} else if (maxRank > rank){
				maxRankYear = fileYear;
				maxRank = rank;
			}

		}
		
		return maxRankYear;
	}

	public Double getAverageRank(String name, String gender){
		DirectoryResource dr = new DirectoryResource();
		Double sumRank = 0.;
		Double countFiles = 0.; 
		for (java.io.File f: dr.selectedFiles()){
			Integer fileYear = Integer.parseInt(f.getName().replaceAll("[^0-9]",""));
			Integer rank = getRank(fileYear, name, gender);
			sumRank += rank;
			countFiles++;
		}
		return sumRank/countFiles;		
	}

	public Integer getTotalBirthsRankedHigher(Integer year, String name, String gender){
		FileResource fr = new FileResource("data/us_babynames_by_year/yob"+year+".csv");
		CSVParser parser = fr.getCSVParser(false);
		
		Integer totalBirths = 0;
		Integer nameRank = getRank(year, name, gender);

		for (CSVRecord r: parser){
			if (!r.get(1).equals(gender)){
				continue;
			}
			if (getRank(year, r.get(0), r.get(1)) < nameRank) {
				totalBirths += Integer.parseInt(r.get(2));
			} else {
				break;
			}			
		}
		return totalBirths;
	}

	public void testTotalBirths () {
		//FileResource fr = new FileResource();
		FileResource fr = new FileResource("data/us_babynames_test/yob2014short.csv");
		totalBirths(fr);
	}

	public void testGetRank(){
		System.out.println(getRank(2012, "Harry", "M"));
	}

	public void testGetName(){
		System.out.println(getName(2014, 1, "F"));
	}

	public void testWhatIsNameInYear(){
		whatIsNameInYear("Emma", 2012, 2014, "F");
	}

	public void testYearOfHighestRank(){
		System.out.println(yearOfHighestRank("Mason", "M"));
	}

	public void testGetAverageRank(){
		System.out.println(getAverageRank("Mason", "M"));
		System.out.println(getAverageRank("Jacob", "M"));
	}

	public void testGetTotalBirthsRankedHigher(){
		System.out.println(getTotalBirthsRankedHigher(2012, "Ethan", "M"));
	}

	public static void main(String[] args){
		exercise e = new exercise();
		// e.testTotalBirths();
		// e.testGetRank();
		// e.testGetName();
		// e.testWhatIsNameInYear();
		// e.testYearOfHighestRank();
		// e.testGetAverageRank();
		// e.testGetTotalBirthsRankedHigher();

		FileResource fr = new FileResource("data/us_babynames_by_year/yob1900.csv");
		e.totalBirths(fr);

		fr = new FileResource("data/us_babynames_by_year/yob1905.csv");
		e.totalBirths(fr);

		// System.out.println(e.getRank(1960, "Emily", "F"));
		// System.out.println(e.getRank(1971, "Frank", "M"));

		// System.out.println(e.getName(1980, 350, "F"));
		// System.out.println(e.getName(1982,450, "M"));

		// e.whatIsNameInYear("Susan", 1972, 2014, "F");
		// e.whatIsNameInYear("Owen", 1974, 2014, "M");

		// System.out.println(e.yearOfHighestRank("Genevieve", "F"));
		// System.out.println(e.yearOfHighestRank("Mich", "M"));

		// System.out.println(e.getAverageRank("Susan", "F"));
		// System.out.println(e.getAverageRank("Robert", "M"));

		// System.out.println(e.getTotalBirthsRankedHigher(1990, "Emily", "F"));
		// System.out.println(e.getTotalBirthsRankedHigher(1990, "Drew", "M"));
	}
}

