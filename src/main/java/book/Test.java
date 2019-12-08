package book;

public class Test {

    public static void main(String[] args) {

        String keyWord = "Terry, Pratchett";

        int coma = keyWord.indexOf(",");
        String firstName = keyWord.substring(0,coma);
        String lastName = keyWord.substring(coma + 2);

        System.out.println(firstName + "," + lastName);
    }
}
