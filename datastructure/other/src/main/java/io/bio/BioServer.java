package io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName BioServer
 * @Description
 * @Author za-yaowei
 * @Date 2021/7/22 12:45
 * @Version 1.0
 */
public class BioServer {

    public static void main(String[] args) throws IOException {
        // 创建服务端套接字，并绑定端口
        ServerSocket serverSocket = new ServerSocket(9999);
        // 等待客户端进行绑定
        System.out.println("等候连接");
        Socket clientSocket = serverSocket.accept();
        System.out.println("有客户端连接了。。。");
        while (true){
            byte[] bytes = new byte[1024];
            // 读取数据方法为阻塞方法
            int read = clientSocket.getInputStream().read(bytes);
            System.out.println("读取客户端数据");
            if (read != -1){
                System.out.println("接收到客户端的数据：" + new String(bytes, 0, read));
            }
            clientSocket.getOutputStream().write("HelloClient".getBytes());
            clientSocket.getOutputStream().flush();
        }
    }
}
