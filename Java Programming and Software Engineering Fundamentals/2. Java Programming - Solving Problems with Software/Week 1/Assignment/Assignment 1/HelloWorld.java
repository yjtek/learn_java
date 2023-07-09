import helper_source_code.edu.duke.*;
public class HelloWorld {
    // public void runHello() {
    public static void main(String[] args){
        FileResource res = new FileResource("hello_unicode.txt");
        for (String line : res.lines()){
            System.out.println(line);
        }
    }
}
