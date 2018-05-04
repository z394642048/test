package com.chaoxing.IO;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadEchoServer {

    private static ExecutorService tp = Executors.newCachedThreadPool();

    static class HandleMsg implements Runnable {

        Socket clientSocket;

        public HandleMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            BufferedReader is = null;
            PrintWriter os = null;
            try {
                //得到输入的信息，并将字节输入流通过InputStreamReader转化成字符缓存流
                is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //创建一个自动刷新的字符打印流，并开启自动刷新功能
                os = new PrintWriter(clientSocket.getOutputStream(), true);
                //从InputStream当中读取客户端所发送的数据
                String inputLine = null;
                long b = System.currentTimeMillis();
                //读取字符缓存流中的信息
                while ((inputLine = is.readLine()) != null) {
                    //把要返回的信息写入字符打印流中，因为开启了自动刷新功能，因此在写入后会自动返回给客户端
                    os.println("将要返回给客户端的信息： "+inputLine);
                }
                long e = System.currentTimeMillis();
                System.out.println("speed:" + (e - b) + "毫秒");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    @Test
    public void test1() {
        ServerSocket echoServer = null;
        Socket clientSocket = null;
        try {
            //创建服务器的套接字，并监听8000端口
            echoServer = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                clientSocket = echoServer.accept();
                System.out.println(clientSocket.getRemoteSocketAddress() + " connect!");
                tp.execute(new HandleMsg(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
