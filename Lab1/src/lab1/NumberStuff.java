
package lab1;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class NumberStuff implements Comparator<NumberStuff>{
    
    public int amount;
    public String word;
    
    public NumberStuff(String s) {
        amount = 1;
        word = s;
    }
    
    public void add() {
        amount++;
    }

    public int compare(NumberStuff t, NumberStuff t1) {
        if (t.amount < t1.amount) {
            return -1;
        } else if (t.amount == t1.amount) {
            return 0;
        } else {
            return 1;
        }
    }
    
    public boolean contains(String s) {
        return true;
    }
    
    public String toString() {
        return word + "," + amount;
    }
}
