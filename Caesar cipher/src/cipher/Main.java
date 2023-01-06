package cipher;
//
//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        String[][] playfairTable = new String[5][5];
//        String keyString = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
//        int t = 0;
//        for(int i = 0; i < 5; i++)
//            for(int j = 0; j < 5; j++){
//                playfairTable[i][j] = String.valueOf(keyString.charAt(t));
//                t += 1;
//            }
//
////        for(int k = 0; k < keyString.length(); k++)
////        {
////            for(int i = 0; i < 5; i++)
////            {
////                for(int j = 0; j < 5; j++)
////                {
////                    playfairTable[i][j] = String.valueOf(keyString.charAt(k));
////                }
////            }
////        }
//        for(int i = 0; i < 5; i++)
//        {
////loop iterates for column
//            for(int j = 0; j < 5; j++)
//            {
////prints the key-table in matrix form
//                System.out.print(playfairTable[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//
//
////        System.out.println("Enter language: 'rus/eng'");
////        Scanner inLanguage = new Scanner(System.in);
////        String language = inLanguage.next();
////        System.out.println("Enter text that you want to cryption: ");
////        Scanner inText = new Scanner(System.in);
////        String text = inText.nextLine();
////        System.out.println("Enter key: ");
////        Scanner inKey = new Scanner(System.in);
////        String key = inKey.nextLine();
////        System.out.println("CRYPTION");
////        CaesarCipher a = new CaesarCipher(text, language, key);
////        String text1 = a.chiper();
////        System.out.println(text1);
////        System.out.println("DECRYPTION");
////        CeaserDecryption b = new CeaserDecryption(text1, language, key);
////        System.out.println(b.dechiper());
////        PlayfairCipher a = new PlayfairCipher(text, language, key);
////        a.chiper();
//
//    }
//}

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        repeatwords("NWASBDWFGFLX");
    }
    private static void repeatwords(String name) {
        String str = name;
        String result = new StringBuilder(str).reverse().toString();
        result = result.replaceAll("(.)(?=.*\\1)", "");
        result = new StringBuilder(result).reverse().toString();
//        System.out.println(result);
        for(int i = 0; i < result.length(); i++)
        {
            Character g = result.charAt(i);
            int count = 0;
            for (int j = 0; j < name.length(); j++) {
                char ch = name.charAt(j);
                if (ch == g) {
                    count++;
                }
            }
            System.out.printf(i +") "+ "There are %d occurrences of %s in %s", count,
                    g, name);
            System.out.println();
        }
    }
}
