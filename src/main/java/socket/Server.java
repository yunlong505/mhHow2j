package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        try {
            //服务端打开端口8888
            ServerSocket ss = new ServerSocket(8888);
            Socket s = ss.accept();
            final InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            byte[] b = new byte[1024];
                            int len = is.read(b);
                            System.out.println(("收到客户端信息:" + new String(b, 0, len)));
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

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


//
//        try {
//            ServerSocket ss = new ServerSocket(10001);
//            Socket s = ss.accept();
//            InputStream is = s.getInputStream();
//            byte[] buf = new byte[1000];
//            int length = is.read(buf);
//            System.out.println("Information from " + s.getLocalAddress().getHostAddress() + " : " + new String(buf, 0, length));
//            is.close();
//            s.close();
//            ss.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}