package jihe;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class settest {
    public static void main(String[] args) {
        TreeSet t = new TreeSet();
        t.add("orange");
        t.add("apple");
        t.add("banana");
        t.add("grape");

        Iterator it = t.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
    /*public static void main(String[] args) {
        HashSet h = new HashSet();
        h.add("1st");
        h.add("2nd");
        h.add(new Integer(3));
        h.add(new Double(4.1));
        h.add("2nd");
        h.add(new Integer(3));
        h.add(new Date());
        System.out.println("开始：size = " + h.size());

        Iterator it = h.iterator();
        while (it.hasNext()){
            Object o = it.next();
            System.out.println(o);
        }

        h.remove("2nd");
        System.out.println("移除元素后：size = " + h.size());
    }*/

}
