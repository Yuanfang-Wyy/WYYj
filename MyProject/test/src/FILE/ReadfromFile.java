package FILE;

import java.io.*;

public class ReadfromFile {
    /**
     *以字节为单位读取文件，常用于读取二进制文件，如图片，视频，音频等
     */
    public static void readfilebybytes(String filename){
        File file = new File(filename);
        InputStream in = null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节: ");
            in = new FileInputStream(file);
            Integer tempbyte;
            while ((tempbyte = in.read()) != -1){
                System.out.write(tempbyte);
            }
            in.close();
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
        try {
            System.out.println();
            System.out.println("以字节为单位读取文件内容，一次读取多个字符");
            //一次读取多个字节
            byte[] tempbyte = new byte[100];
            Integer byteread = 0;
            in = new FileInputStream(filename);
            ReadfromFile.showAvailablebytes(in);
            //读取多个字节到字节数组中，byteread为一次读取的字节数
            while ((byteread = in.read(tempbyte)) != -1){
                System.out.write(tempbyte,0,byteread);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (in != null){
                try {
                    in.close();
                }catch (IOException e){

                }
            }
        }
    }

    /**
     * 以字符为单位读取文件，常应用于读取文本，数字等类型的文件
     */
    public static void readfilebychar(String filename){
        File file = new File(filename);
        Reader reader = null;
        try {
            System.out.println();
            System.out.println();
            System.out.println("以字符为单位读取文件内容，一次读取一个字节：");
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar = 0;
            while ((tempchar = reader.read()) != -1){
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char)tempchar) != '\n'){
                    System.out.print((char)tempchar);
                }
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            System.out.println();
            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
            char[] tempchar = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(filename));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread  = reader.read(tempchar)) != -1){
                if ((charread == tempchar.length) && (tempchar[tempchar.length - 1] != '\r')){
                    System.out.print(tempchar);
                }else {
                    for (int i=0; i<tempchar.length-1; i++){
                        if (tempchar[i] == '\r'){
                            continue;
                        }else {
                            System.out.print(tempchar[i]);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (reader != null){
                try {
                    reader.close();
                }catch (IOException e){

                }
            }
        }
    }

    /**
     * 以行为单位读取文件，常用于面向行的格式化文件
     */
    public static void readfilebyline(String filename){
        File file = new File(filename);
        BufferedReader reader = null;

        try {
            System.out.println();
            System.out.println();
            System.out.println("以行为单位读取文件，一次读取一行: ");
            reader = new BufferedReader(new FileReader(file));
            String tempstring = null;
            int line = 1;
            while ((tempstring = reader.readLine()) != null){
                System.out.println("line " + line + ":" + tempstring);
                line++;
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader != null){
                try {
                    reader.close();
                }catch (IOException e){}
            }
        }
    }

    /**
     * 随机读取文件内容
     */
    public static void readfilebyrandom(String filename){
        RandomAccessFile randomfile = null;
        try {
            System.out.println();
            System.out.println("随机读取一段文件内容：");
            //打开一个随机访问文件流，按只读方式
            randomfile = new RandomAccessFile(filename,"r");
            //文件长度，字节数
            long filelength = randomfile.length();
            //读文件的起始位置
            int beginindex = (filelength > 0) ? 0:0;
            randomfile.seek(beginindex);
            byte[] bytes = new byte[10];
            int byteread = 0;
            //一次读取10个字符，如果内容不足10个字符，则读取剩下的内容
            //将读取的内容赋值给byteread
            while ((byteread = randomfile.read(bytes)) != -1){
                System.out.write(bytes,0,byteread);
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (randomfile != null){
                try {
                    randomfile.close();
                }catch (IOException e){

                }
            }
        }
    }


    /**
     * 将内容追加到文件尾部
     */
    public static class AppendToFile{
        /**
         * A方法追加文件：使用RandomAccessFile
         */
        public  static void appendMethodA(String filename,String context){
            try {
                RandomAccessFile randomfile = new RandomAccessFile(filename,"rw");
                //文件长度，字节数
                long fileLength = randomfile.length();
                //将文件指针移动到文件尾部
                randomfile.seek(fileLength);
                randomfile.writeBytes(context);
                randomfile.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        /**
         * 用B方法追加文件：使用FileWriter
         */
        public static void appendMethodB(String filename,String context){
            try {
                //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
                //FileWriter writer = new FileWriter(filename,true);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename,true),"UTF-8"));

                writer.write(context,0,context.length());
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }


    /**
     * 显示输入流中还剩的字节数
     */
    private static void showAvailablebytes(InputStream in){
        try {
            System.out.println("当前字节输入流中的字节数为：" + in.available());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filename = "C:/Users/WangY/Desktop/新建文件夹/1.txt";
        ReadfromFile.readfilebybytes(filename);
        ReadfromFile.readfilebychar(filename);
        ReadfromFile.readfilebyline(filename);
        ReadfromFile.readfilebyrandom(filename);

        String context = "今天我感冒了！";
        //ReadfromFile.AppendToFile.appendMethodA(filename,context);  //会乱码
        ReadfromFile.AppendToFile.appendMethodB(filename,context);

    }
}
