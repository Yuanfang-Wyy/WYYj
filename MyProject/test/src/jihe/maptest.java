package jihe;

import java.util.*;

public class maptest {
    public static void main(String[] args) {
        Map<Integer,String> map1 = new HashMap<Integer, String>();
        map1.put(1,"aaa");
        map1.put(2,"bbb");
        ////keySet方法和entrySet方法。keySet是按照关键字来建立set返回Object.
        Iterator<Integer> iterator = map1.keySet().iterator();
        while (iterator.hasNext()){
            Object key = iterator.next();
            System.out.println(key + "->" + map1.get(key));
        }

        //entrySet是使用key和value建立set,返回Object类型
        Iterator iterator1 = map1.entrySet().iterator();
        while (iterator1.hasNext()){
            Map.Entry<Integer,String> entry = (Map.Entry<Integer,String>) iterator1.next();
            System.out.println(entry.getKey() + "->" + entry.getValue());

        }

        Hashtable<String,String> tab = new Hashtable<String, String>();
        tab.put("a","aaa");
        tab.put("b","bbb");
        tab.put("c","ccc");
        tab.put("d","ddd");

        Iterator<String> it1 = tab.keySet().iterator();
        while (it1.hasNext()){
            Object key = it1.next();
            System.out.println(key + "->" + tab.get(key));
        }

        TreeMap<String,String> tmp = new TreeMap<String, String>();
        tmp.put("aaa","aaa");
        tmp.put("bbb","bbb");
        tmp.put("ccc","ccc");
        tmp.put("ddd","ddd");

        Iterator<String> t1 = tmp.keySet().iterator();
        while (t1.hasNext()){
            Object key = t1.next();
            System.out.println(key + "->" + tmp.get(key));
        }


    }
}
