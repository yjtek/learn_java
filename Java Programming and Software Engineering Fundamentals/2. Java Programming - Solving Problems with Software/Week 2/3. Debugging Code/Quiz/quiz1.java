package Quiz;

public class quiz1 {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1 || index >= input.length() - 3) {
                break;
            }

            String found = input.substring(index+1, index+4);
            System.out.println(found);

            // System.out.println(index);
            index = input.indexOf("abc", index+3);
            // System.out.println(index);
        }
    }
    
    public void test() {
        // findAbc("abcdefabcghi");
        // findAbc("abcd");
        // findAbc("abcdabc");
        // findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
        findAbc("abcabcabcabca");
    }

    public static void main(String[] args){
        quiz1 q1 = new quiz1();
        q1.test();
    }
    
}
