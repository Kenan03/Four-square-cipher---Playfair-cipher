package cipher;

import java.util.Random;
import java.util.Scanner;

public class PlayfairCipher
{
    private int length = 0;
    public String [][] table1;
    public String [][] table2;
    public String [][] table3;
    public String [][] table4;
    public static void main(String args[])
    {
        PlayfairCipher pf = new PlayfairCipher();
    }

    private PlayfairCipher() {
        makefourtable();

        System.out.print("Enter the plaintext to be encipher: ");
        Scanner sc2 = new Scanner(System.in);
        String input = parseString(sc2);
        while(input.equals(""))
            input = parseString(sc2);
        String[] output = (cipher(input));

        String word;
        String newoutput = null;
        for(int j = 0; j < output.length ; j++)
        {
            word = encodeDigraph(output[j]);
            if(j == 0)
                newoutput = word;
            else
                newoutput += word;
        }
        System.out.println(newoutput + " - Закодированное слово");

        //repeatwords(newoutput);

//        String[] fordecode = cipher(newoutput);
        String[] fordecode = new String[newoutput.length() / 2];
        for(int j = 0; j < length ; j++)
        {
            fordecode[j] = newoutput.charAt(2 * j) +""+ newoutput.charAt(2 * j + 1);
        }
        String wordtwo;
        String newfordecode = null;
        for(int j = 0; j < fordecode.length ; j++)
        {
            wordtwo = decode(fordecode[j]);
            if(j == 0)
                newfordecode = wordtwo ;
            else
                newfordecode += wordtwo;
        }
        System.out.println(newfordecode + " - Раскодированное слово");

        this.keyTable(table1);
        this.keyTable(table2);
        this.keyTable(table3);
        this.keyTable(table4);
    }

    private void makefourtable() {
        table1 = new String[5][5];
        String keyString = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        int t = 0;
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                table1[i][j] = String.valueOf(keyString.charAt(t));
                t += 1;
            }
        }
        t = 0;
        table4 = new String[5][5];
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                table4[i][j] = String.valueOf(keyString.charAt(t));
                t += 1;
            }
        }

        System.out.print("Enter the key #1 for playfair cipher, or enter '-' for random key: ");
        Scanner sc = new Scanner(System.in);
        String key = parseString(sc);
        System.out.println(key);
        while (key.equals(""))
            key = parseString(sc);
        table2 = this.cipherTable(key);

        System.out.print("Enter the key #2 for playfair cipher: or enter '-' for random key: ");
        Scanner sc1 = new Scanner(System.in);
        String key1 = parseString(sc1);
        System.out.println(key1);
        while (key1.equals(""))
            key1 = parseString(sc1);
        table3 = this.cipherTable(key1);

    }

    public static String[] generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[20]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    private String parseString(Scanner sc) {
        String parse = sc.nextLine();
        if(parse.equals("-"))
        {
            parse = null;
            String[] keys = generateRandomWords(1);
            for(int i = 0; i < keys.length; i++)
            {
                if(i == 0) {
                    parse = keys[i];
                }
                else {
                    parse += keys[i];
                }
            }
        }
        parse = parse.toUpperCase();
        parse = parse.replaceAll("[^A-Z]", "");
        parse = parse.replace("J", "I");
        return parse;
    }

    private String[][] cipherTable(String key) {
        String[][] playfairTable = new String[5][5];
        String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                playfairTable[i][j] = "";
        for(int k = 0; k < keyString.length(); k++)
        {
            boolean repeat = false;
            boolean used = false;
            for(int i = 0; i < 5; i++)
            {
                for(int j = 0; j < 5; j++)
                {
                    if(playfairTable[i][j].equals("" + keyString.charAt(k)))
                    {
                        repeat = true;
                    }
                    else if(playfairTable[i][j].equals("") && !repeat && !used)
                    {
                        playfairTable[i][j] = "" + keyString.charAt(k);
                        used = true;
                    }
                }
            }
        }
        return playfairTable;
    }

    private String[] cipher(String in) {
        length = (int) in.length() / 2 + in.length() % 2;

        for(int i = 0; i < (length - 1); i++)
        {
            if(in.charAt(2 * i) == in.charAt(2 * i + 1))
            {
                in = new StringBuffer(in).insert(2 * i + 1, 'X').toString();
                length = (int) in.length() / 2 + in.length() % 2;
            }
        }

        String[] digraph = new String[length];
        for(int j = 0; j < length ; j++)
        {
            if(j == (length - 1) && in.length() / 2 == (length - 1))
                in = in + "X";
            digraph[j] = in.charAt(2 * j) +""+ in.charAt(2 * j + 1);
        }
        System.out.println(in + " - Слово для кодирования");
        return digraph;
    }

    private String encodeDigraph(String di) {
        int x = 0;
        int y = 0;
        int t = 0;
        int z = 0;
        String res1 = String.valueOf(di.charAt(0));
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++)
            {
                String res = table1[i][j];
                if(res1.equals(res))
                {
                    x = i;
                    y = j;
                }
            }
        }
        res1 = String.valueOf(di.charAt(1));
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++)
            {
                String res = table4[i][j];
                if(res1.equals(res))
                {
                    t = i;
                    z = j;
                }
            }
        }
//        System.out.println(x + ";" + z + "\n" + t + ";" + y);
        String fi = table2[x][z] + table3[t][y];
//        System.out.println("-------------");
        return fi;

    }

    private String decode(String out) {
        int x = 0;
        int y = 0;
        int t = 0;
        int z = 0;
        String res1 = String.valueOf(out.charAt(0));
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++)
            {
                String res = table2[i][j];
                if(res1.equals(res))
                {
                    x = i;
                    y = j;
                }
            }
        }
        res1 = String.valueOf(out.charAt(1));
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++)
            {
                String res = table3[i][j];
                if(res1.equals(res))
                {
                    t = i;
                    z = j;
                }
            }
        }
//        System.out.println(x + ";" + z + "\n" + t + ";" + y);
        String fi = table1[x][z] + table4[t][y];
//        System.out.println("-------------");
        return fi;
    }

    private void keyTable(String[][] printTable) {
        System.out.print("Playfair Cipher Key Matrix: ");
        System.out.println();
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                System.out.print(printTable[i][j]+" ");
            }System.out.println();
        }System.out.println();
    }

    private void repeatwords(String name) {
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

