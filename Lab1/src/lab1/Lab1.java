
package lab1;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;



public class Lab1 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        try {
            char [] CapitalLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            char [] Punctuation = {'~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '-', '=', '{', '}', '|', '[', ']', '\\', '"', '\'', ':', ';', '<', '>', '?', ',', '.', '/'};
            char [] PunctuationM = {'~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '-', '=', '{', '}', '|', '[', ']', '\\', '"',  ':', ';', '<', '>', '?', ',', '.', '/', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '\n'};
            char [] Numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
            PrintWriter pw = new PrintWriter("result.txt", "UTF-8");
            try {
                pw.println("The number of lower case t's are : " + findChar("text.txt", 't'));
                int tmp;
                tmp = findChar("text.txt", 't') + findChar("text.txt", 't');
                pw.println("The number of t's are : " + tmp);
                pw.println("The number of capital letters is : " + findChars("text.txt", CapitalLetters));
                pw.println("Number occurences in the text : " + findCharsString("text.txt", Numbers));
                pw.println("The number of punctuations in the text is : " + findChars("text.txt", Punctuation));
                tmp = 0;
                String tmpS = "";
                for (int i : findWords("text.txt", " the ")) {
                    tmpS+=i;
                    tmpS+=", ";
                }
                pw.println("The places that the word 'the' occurs : " + tmpS);
                tmpS = "";
                for (String i : findUniqueWords("text.txt", PunctuationM)) {
                    tmpS += i;
                    tmpS += ", ";
                }
                pw.println("The number of unique words is " + findUniqueWords("text.txt", PunctuationM).size());
                pw.println("The unique words are : " + tmpS);
                
                tmpS = "";
                for (NumberStuff i : findWords("text.txt", PunctuationM)) {
                    tmpS += i.toString();
                    tmpS += ", \n";
                }
                
                pw.println("The Unique Words and Counts are : \n" + tmpS);
                
                pw.println("The Average Word Length Is :" + FindAverageWordLength("text.txt", PunctuationM));
                
            } catch (IOException e) {
                
            }
            
            
            pw.close();
            
        } catch (FileNotFoundException e) {
            
        }
        
        
    }
    
    static int findChar(String s, char c) throws IOException {
        int tmpI = 0;
        FileReader f = new FileReader(s);
        while (f.ready()) {
            if (f.read() == c) {
                tmpI++;
            }
        }
        
        f.close();
        return tmpI;
    }
    
    static int findChars(String s, char[] c) throws FileNotFoundException, IOException {
        int tmpI = 0;
        FileReader f = new FileReader(s);
        while (f.ready()) {
            int j = f.read();
            for (char _c : c) {
                if (_c == j) {
                    tmpI++;
                }
            }
        }
        
        return tmpI;
    }
    
    static String findCharsString(String s, char[] c) throws FileNotFoundException, IOException {
        String tmpI = "";
        FileReader f = new FileReader(s);
        while (f.ready()) {
            int j = f.read();
            for (char _c : c) {
                if (_c == j) {
                    tmpI+=(char) j;
                    tmpI+=", ";
                }
            }
        }
        
        return tmpI;
    }
    
    static int[] findWords(String s, String c) throws FileNotFoundException, IOException {
        List<Integer> tmp = new LinkedList();
        List<Integer> tmpPos = new LinkedList();
        FileReader f = new FileReader(s);
        String tmpString = "";
        while (f.ready()) {
            int i = f.read();
            tmpString += (char) i;
        }
        tmpString = tmpString.toLowerCase();
        for (int i = 0; i < tmpString.length(); i++) {
            tmp.add((int) tmpString.charAt(i));
        }
        
        for (int i = 0; i < tmp.size(); i++) {
            if (tmp.get(i).equals((int) c.charAt(0))) {
                for (int j = 0; j < c.length(); j++) {
                    if (c.charAt(j) == tmp.get(i + j)) {
                        if (j == c.length() - 1) {
                            tmpPos.add(i + 2);
                        }
                    } else {
                        break;
                    }
                }
            }
            
        }
        
        int[] result = new int[tmpPos.size()];
        for (int i = 0; i < tmpPos.size(); i++) {
            result[i] = tmpPos.get(i);
        }
        return result;
    }
    
    static List<String> findUniqueWords(String s, char[] p) throws FileNotFoundException, IOException {
        String tmpWord = "";
        List<String> listWords = new LinkedList();
        FileReader f = new FileReader(s);
        
        boolean test = true;
        
        int tmpI = 0;
        while (f.ready()) {
            tmpI = f.read();
            for (char c : p) {
                if (tmpI != c) {
                    
                } else {
                    test = false;
                }
            }
            if (test) {
                tmpWord+=(char) tmpI;
            } else {
                if (listWords.contains(tmpWord.toLowerCase())) {
                    
                } else {
                    if (tmpWord.length() > 0) {
                        listWords.add(tmpWord.toLowerCase());
                    }
                }
                tmpWord = "";
                test = true;
            }
        }
        
        return listWords;
    }
    
    static List<NumberStuff> findWords(String s, char[] p) throws FileNotFoundException, IOException {
        String tmpWord = "";
        List<NumberStuff> ns = new LinkedList();
        FileReader f = new FileReader(s);
        
        List<Integer> tmp1 = new LinkedList();
        String tmpString = "";
        while (f.ready()) {
            int i = f.read();
            tmpString += (char) i;
        }
        tmpString = tmpString.toLowerCase();
        for (int i = 0; i < tmpString.length(); i++) {
            tmp1.add((int) tmpString.charAt(i));
        }
        
        
        
        boolean test = true;
        
        for (int tmpI : tmp1) {
            for (char c : p) {
                if (tmpI != c) {
                    
                } else {
                    test = false;
                }
            }
            if (test) {
                tmpWord+=(char) tmpI;
            } else {
                boolean tmp = true;
                for (int i = 0; i < ns.size(); i++) {
                    if (ns.get(i).word.equals(tmpWord.toLowerCase())) {
                        ns.get(i).add();
                        tmp = false;
                    }
                }
                
                if (tmp) {
                    ns.add(new NumberStuff(tmpWord));
                }
                tmpWord = "";
                test = true;
            }
        }
        
        ns.sort(null);
        
        return ns;
    }
    
    static int FindAverageWordLength (String s, char [] p) throws IOException {
        int tmp = 0;
        int size = 0;
        int count = 0;
        
        List<String> l = findUniqueWords(s, p);
        for (int i = 0; i < l.size(); i++) {
            size += l.get(i).length();
            count++;
        }
        
        tmp = size / count;
        
        return tmp;
    }
}


