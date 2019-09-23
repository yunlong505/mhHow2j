package file;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class TestFile {

    public static void main(String[] args) {
        // 绝对路径
//        File f1 = new File("E:/000002/aaa");
//        System.out.println("f1的绝对路径：" + f1.getAbsolutePath());
//        // 相对路径,相对于工作目录，如果在eclipse中，就是项目目录
//        File f2 = new File("LOL.exe");
//        System.out.println("f2的绝对路径：" + f2.getAbsolutePath());
//
//        // 把f1作为父目录创建文件对象
//        File f3 = new File(f1, "LOL.exe");
//        File f4 = new File(f1, "DOTA.exe");
//f3.renameTo(f4);
//        System.out.println("f3的绝对路径：" + f3.getAbsolutePath());
//   //     File[] fs = f1.listFiles();
//        System.out.println(f1.getParentFile());
//        System.out.println(f1.getParent());



      //  f1.mkdirs();
//        f3.getParentFile().mkdirs();
//        try {
//            f3.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        int eachSize = 100 * 1024; // 100k
//        File srcFile = new File("E:/000002/aaa/eclipse.exe");
//        splitFile(srcFile, eachSize);

 //       murgeFile("E:/000002/aaa/", "eclipse.exe");
//        File f = new File("E:/000002/aaa/eclipse.exe");
//        try (FileInputStream fis = new FileInputStream(f);) {
//            byte[] all = new byte[(int) f.length()];
//            fis.read(all);
//            System.out.println("all的长度："+all.length);
//            //文件中读出来的数据是
//            System.out.println("文件中读出来的数据是：");
//            for (byte b : all)
//            {
//                int i = b&0x000000ff;  //只取16进制的后两位
//                System.out.println(Integer.toHexString(b));
//            }
//            System.out.println("把这个数字，放在GBK的棋盘上去：");
//            String str = new String(all,"UTF-8");
//            System.out.println(str);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

//        String str = "中";
//        showCode(str);

        // 准备文件lol.txt其中的内容是
        // garen kill teemo
        // teemo revive after 1 minutes
        // teemo try to garen, but killed again
//        File f = new File("E:/000002/aaa/eclipse.exe");
////        // 创建文件字符流
////        // 缓存流必须建立在一个存在的流的基础上
////        byte []  all = new byte[(int) f.length()];
////        try (
////                FileInputStream fis = new FileInputStream(f);
////            //    FileReader fr = new FileReader(f);
////                BufferedInputStream bis = new BufferedInputStream(fis)
////              //  BufferedReader br = new BufferedReader(fis);
////        )
////        {
////
////            while((bis.read(all))!=-1){
////                System.out.println(new String(all,0,all.length));
////            }
////
////            for (byte b : all){
////
////                System.out.println(b);
////            }
//////            while (true) {
//////                // 一次读一行
//////                String line = br.readLine();
//////                bis.read(all);
//////
//////                if (null == line)
//////                    break;
//////                System.out.println(line);
//////            }
////        } catch (IOException e) {
////            // TODO Auto-generated catch block
////            e.printStackTrace();
////        }

        // 控制台输入
//        try (InputStream is = System.in;) {
//            while (true) {
//                // 敲入a,然后敲回车可以看到
//                // 97 13 10
//                // 97是a的ASCII码
//                // 13 10分别对应回车换行
//                int i = is.read();
//                System.out.println(i);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Scanner s = new Scanner(System.in);
//        int a = s.nextInt();
//        System.out.println("第一个整数："+a);
//        int b = s.nextInt();
//        System.out.println("第二个整数："+b);
        Thread t1= new Thread(){
            public void run(){
                int seconds =0;

                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.printf("已经玩了LOL %d 秒%n", seconds++);

                }
            }
        };
        t1.setDaemon(true);
        t1.start();
        for (int i = 0 ;i<5;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    private static void showCode(String str) {
        String[] encodes = { "BIG5", "GBK", "GB2312", "UTF-8", "UTF-16", "UTF-32" };
        for (String encode : encodes) {
            showCode(str, encode);
        }

    }

    private static void showCode(String str, String encode) {
        try {
            System.out.printf("字符: \"%s\" 的在编码方式%s下的十六进制值是%n", str, encode);
            byte[] bs = str.getBytes(encode);

            for (byte b : bs) {
                int i = b&0xff;
        //        System.out.print(Integer.toHexString(i) + "\t");
       //         System.out.print(Integer.toHexString(b) + "\t");
   //            System.out.print(Integer.toHexString(b) + "\t");
          //      System.out.print(b + "\t");
          //     System.out.print(Integer.toBinaryString(b&0b11111111) + "\t");
               System.out.print(Integer.toBinaryString(b) + "\t");
          //    int i =b&0b11111111;
           //    System.out.print(i + "\t");
          //      System.out.print(Integer.toHexString(b&0b11111111) + "\t");

            }
            System.out.println();
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            System.out.printf("UnsupportedEncodingException: %s编码方式无法解析字符%s\n", encode, str);
        }




    }

    /**
     * 拆分的思路，先把源文件的所有内容读取到内存中，然后从内存中挨个分到子文件里
     * @param srcFile 要拆分的源文件
     * @param eachSize 按照这个大小，拆分
     */
    private static void splitFile(File srcFile, int eachSize) {

        if (0 == srcFile.length())
            throw new RuntimeException("文件长度为0，不可拆分");

        byte[] fileContent = new byte[(int) srcFile.length()];
        // 先把文件读取到数组中
        try {
            FileInputStream fis = new FileInputStream(srcFile);
            fis.read(fileContent);
            fis.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 计算需要被划分成多少份子文件
        int fileNumber;
        // 文件是否能被整除得到的子文件个数是不一样的
        // (假设文件长度是25，每份的大小是5，那么就应该是5个)
        // (假设文件长度是26，每份的大小是5，那么就应该是6个)
        if (0 == fileContent.length % eachSize)
            fileNumber = (int) (fileContent.length / eachSize);
        else
            fileNumber = (int) (fileContent.length / eachSize) + 1;

        for (int i = 0; i < fileNumber; i++) {
            String eachFileName = srcFile.getName() + "-" + i;
            File eachFile = new File(srcFile.getParent(), eachFileName);
            byte[] eachContent;

            // 从源文件的内容里，复制部分数据到子文件
            // 除开最后一个文件，其他文件大小都是100k
            // 最后一个文件的大小是剩余的
            if (i != fileNumber - 1) // 不是最后一个
                eachContent = Arrays.copyOfRange(fileContent, eachSize * i, eachSize * (i + 1));
            else // 最后一个
                eachContent = Arrays.copyOfRange(fileContent, eachSize * i, fileContent.length);

            try {
                // 写出去
                FileOutputStream fos = new FileOutputStream(eachFile);
                fos.write(eachContent);
                // 记得关闭
                fos.close();
                System.out.printf("输出子文件%s，其大小是 %d字节%n", eachFile.getAbsoluteFile(), eachFile.length());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static void murgeFile(String folder, String fileName) {

        try {
            // 合并的目标文件
            File destFile = new File(folder, fileName);
            FileOutputStream fos = new FileOutputStream(destFile);
            int index = 0;
            while (true) {
                //子文件
                File eachFile = new File(folder, fileName + "-" + index++);
                //如果子文件不存在了就结束
                if (!eachFile.exists())
                    break;

                //读取子文件的内容
                FileInputStream fis = new FileInputStream(eachFile);
                byte[] eachContent = new byte[(int) eachFile.length()];
                fis.read(eachContent);
                fis.close();

                //把子文件的内容写出去
          //     fos.write(eachContent);
               fos.write("asas".getBytes());
                fos.flush();
                System.out.printf("把子文件 %s写出到目标文件中%n",eachFile);
            }

            fos.close();
            System.out.printf("最后目标文件的大小：%,d字节" , destFile.length());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}