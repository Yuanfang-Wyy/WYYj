package justForTest.src;

import org.apache.commons.lang.RandomStringUtils;

public class test01 {
    public static void main(String[] args) {
        System.out.println(RandomStringUtils.randomAlphanumeric(15));
        System.out.println(RandomStringUtils.random(10));
        System.out.println(RandomStringUtils.randomAlphabetic(23));
        System.out.println( RandomStringUtils.randomAlphanumeric(10000).getBytes());
    }
}
