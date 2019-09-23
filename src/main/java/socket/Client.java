package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {
            //连接到本机的8888端口
            Socket s = new Socket("127.0.0.1", 8888);
            final InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            byte[] b = new byte[1024];
                            int len = is.read(b);
                            System.out.println("收到服务端信息:" + new String(b, 0, len));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();

            while (true) {
                Scanner sc = new Scanner(System.in);
                String str = sc.next();
                os.write(str.getBytes());
            }


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


//        try {
//            Socket s = new Socket("192.168.2.62", 10001);
//
//            OutputStream os = s.getOutputStream();
//            os.write("hello, TCP!".getBytes());
//            os.close();
//            s.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}