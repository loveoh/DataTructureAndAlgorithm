package io.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @ClassName BioClient
 * @Description
 * @Author za-yaowei
 * @Date 2021/7/26 12:41
 * @Version 1.0
 */
public class BioClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",9999);
        socket.getOutputStream().write("hello bio".getBytes());
        socket.getOutputStream().flush();
        System.out.println("客户端发送完数据");
        byte[] bytes = new byte[1024];
        int read = socket.getInputStream().read(bytes);
        System.out.println("接收到服务端的数据"+ new String(bytes));
        socket.close();

    }
}
