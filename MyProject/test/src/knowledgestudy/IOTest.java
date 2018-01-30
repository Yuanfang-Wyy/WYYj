package knowledgestudy;
import java.io.*;

public class IOTest {
    /*public void writefile(String filepath, String context){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filepath);
            byte[] array = context.getBytes();
            fos.write(array);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        IOTest a2 = new IOTest();
        String filepath = "C:/Users/WangY/Desktop/新建文件夹/1.txt";
        String content = "今天是2017/11/30,天气不太好" ;
        a2.writefile(filepath,content);
    }*/

    //读取一个文件的内容,读取整个文件，读取大文件可能会报错，小文件的话，性能较好
    public String readFile(String filePath){
        FileInputStream file = null;
        String result = "";
        try {
            file = new FileInputStream(filePath);
            int size = file.available();
            byte[] array = new byte[size];
            file.read(array);
            result = new String(array, "UTF-8");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (file != null){
                try {
                    file.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return result;

    }

    public static void main(String[] args) {
        IOTest a1 = new IOTest();
        String filepath = "C:/Users/WangY/Desktop/新建文件夹/1.txt";
        String result = a1.readFile(filepath);
        System.out.println(result);
    }
/*    //读取一个文件的内容,一行一行去读取，可读取大文件
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null){
                result.append(System.lineSeparator() + s);
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result.toString();

    }

    public static void main(String[] args) {
        File f = new File("C:/Users/WangY/Desktop/新建文件夹/1.txt");
        System.out.println(txt2String(f));
    }*/
    //读取文件的长度
/*    public static void main(String[] args) {
        int count = 0;
        InputStream streamReader = null;
        try {
            streamReader = new FileInputStream(new File("C:/Users/WangY/Desktop/新建文件夹/1.txt"));
            while (streamReader.read() != -1){
                count++;
            }
            System.out.println("文件长度是: " + count + "字节");
        }catch (final IOException e){
            e.printStackTrace();
        }finally {
            try {
                streamReader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }*/
    //创建文件
/*    public static void createfile(){
        File f = new File("C:\\Users\\WangY\\Desktop\\新建文件夹\\1.txt");
        try {
            f.createNewFile();
            System.out.println("该分区大小"+f.getTotalSpace()/(1024*1024*1024)+"G");

            System.out.println("文件名: " + f.getName());
            System.out.println("文件父目录字符串: " + f.getParent());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        createfile();
    }*/
}
