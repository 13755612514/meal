package com.xyc.mealoperation.service;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MySocked {
    public static void main(String[] args) throws Exception{
        ExecutorService threadPool = Executors.newCachedThreadPool();

        ServerSocket server = new ServerSocket(8080);
        System.out.println("服务器启动：监听端口号：8080");
        while (true){
            //获取一个套接字(阻塞)
            final Socket socket = server.accept();
            System.out.println("新的客户端连接");
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }
    public static void handler(Socket socket){
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();

            while (true){
                //读取数据
                int read = inputStream.read(bytes);
                if (read != -1){
                    System.out.println(new String(bytes,0,read));
                }else {
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                System.out.println("socket关闭");
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
