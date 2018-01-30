package jihe;

import java.util.ArrayList;
import java.util.List;

public class list {
    public static void main(String[] args) {
        List<String> person = new ArrayList<>();
        person.add("test1");
        person.add("test2");
        person.add("test3");
        person.add("test4");
        person.add("test5");
        person.add("test6");
        person.add("test7");
        person.add("test8");

        person.remove(4);
        person.remove("test4");

        String per = "";
        per = person.get(2);
        System.out.println(per);
        for (int i=0; i< person.size(); i++){
            System.out.println(person.get(i));
        }

        List<String> fruits = new ArrayList<>();
        fruits.add("苹果");
        fruits.add("香蕉");
        fruits.add("橘子");
        fruits.add("桃子");
        for (int i = 0; i < fruits.size(); i++){
            System.out.println(fruits.get(i));
        }
        String appleString = "苹果";
        System.out.println("fruits中是否包含苹果: " + fruits.contains(appleString));

        if (fruits.contains(appleString)){
            System.out.println("我喜欢吃苹果");
        }else {
            System.out.println("我不喜欢吃苹果");
        }

        String a = "白龙马";
        String b = "沙和尚";
        String c = "猪八戒";
        String d = "唐僧";
        String e = "孙悟空";

        List<String> people = new ArrayList<>();
        people.add(a);
        people.add(b);
        people.add(c);
        people.add(0,d);
        people.add(1,e);

        for (String str:people){
            System.out.println(str);
        }

        people = people.subList(1,4);
        for (int i=0; i < people.size(); i++){
            System.out.println("新的list包含的元素是: " + people.get(i));
        }

        if (people.isEmpty()){
            System.out.println("people is empty");
        }else {
            System.out.println("people is not empty");
        }
        System.out.println(people.iterator());

        //将集合转化为字符串
        String liString = "";
        liString = people.toString();
        System.out.println("将集合转化为字符串: " + liString);


        //将集合转化为指定类型
        //1.默认类型
        List<Object> listString = new ArrayList<>();
        for (int i = 0; i < person.size(); i++){
            listString.add(person.get(i));
        }
        //2.指定类型
        List<StringBuffer> lst = new ArrayList<>();
        for (String str : person){
            lst.add(StringBuffer(str));
        }
        System.out.println(lst);
    }
    private static StringBuffer StringBuffer(String str){
        return null;
    }
}
