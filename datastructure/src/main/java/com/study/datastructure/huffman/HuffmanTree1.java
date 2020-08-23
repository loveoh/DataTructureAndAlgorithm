package com.study.datastructure.huffman;

import java.util.*;

/**
 * Created by loveoh on 2020/8/23.
 */
public class HuffmanTree1 {

    public static void main(String[] args) {
        String str = "can you can a can as a can canner can a can.";
        HuffmanTree1 huffmanTree = new HuffmanTree1();
        byte[] bytes = str.getBytes();
        // 使用huffman进行编码
        byte[] result = huffmanTree.huffmanZip(bytes);
        // 使用huffman进行解码
        byte[] newByte = huffmanTree.unZip(huffmanCodes, result);
        String newStr = new String(newByte);
        System.out.println(newStr);
    }

    /**
     * 根据huffman解码
     *
     * @param huffmanCodes
     * @param result
     * @return
     */
    private byte[] unZip(Map<Byte, String> huffmanCodes, byte[] result) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            byte b = result[i];
            boolean flag = i == result.length - 1;
            String str = byteToBitStr(!flag, b);
            sb.append(str);
        }
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> byteList = new ArrayList<>();
        for (int i = 0; i < sb.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String s = sb.substring(i, i + count);
                 b = map.get(s);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            byteList.add(b);
            i += count;
        }
        byte[] bytes = new byte[byteList.size()];
        for (int i = 0 ;i<byteList.size();i++){
            bytes[i] = byteList.get(i);
        }
        return bytes;
    }

    private String byteToBitStr(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }


    /**
     * 把一个byte数组进行huffman编码
     *
     * @param bytes
     * @return
     */
    public byte[] huffmanZip(byte[] bytes) {
        // 先统计一个byte数组中字符出现的次数，并放入node集合中
        List<Node> nodeList = createNodeList(bytes);
        System.out.println(nodeList);
        // 创建一个huffman树
        Node node = createHuffmanTree(nodeList);
        // 创建一个huffman编码表
        Map<Byte, String> huffmanCodes = createHuffmanCodes(node);
        System.out.println(huffmanCodes);
        // 编码
        byte[] byt = zip(bytes, huffmanCodes);
        return byt;
    }

    /**
     * 根据huffman编码表进行编码
     *
     * @param bytes
     * @param huffmanCodes
     * @return
     */
    private byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder sb = new StringBuilder();
        for (byte key : bytes) {
            sb.append(huffmanCodes.get(key));
        }
        int len;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }
        //用于存储压缩后的byte数组
        byte[] result = new byte[len];
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String strByte;
            if (i + 8 > sb.length()) {
                strByte = sb.substring(i);
            } else {
                strByte = sb.substring(i, i + 8);
            }
            byte b = (byte) Integer.parseInt(strByte, 2);
            result[index] = b;
            index++;
        }
        return result;
    }

    //存储huffman编码表
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    // 用于临时存储路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 创建huffman编码表
     * 根节点到左子树的路径为0,根节点到右子树的路径为1
     *
     * @param node
     * @return
     */
    private Map<Byte, String> createHuffmanCodes(Node node) {
        if (node == null) {
            return null;
        }
        getCode(node.left, "0", stringBuilder);
        getCode(node.right, "1", stringBuilder);
        return huffmanCodes;
    }

    private void getCode(Node node, String s, StringBuilder stringBuilder) {
        StringBuilder sb = new StringBuilder(stringBuilder);
        sb.append(s);
        if (node.data == null) {
            getCode(node.left, "0", sb);
            getCode(node.right, "1", sb);
        } else {
            huffmanCodes.put(node.data, sb.toString());
        }
    }

    /**
     * 创建huffman树
     *
     * @param nodeList
     * @return
     */
    private Node createHuffmanTree(List<Node> nodeList) {
        while (nodeList.size() > 1) {
            // 针对list进行排序
            Collections.sort(nodeList);
            // 取出最小的二叉树
            Node left = nodeList.get(nodeList.size() - 1);
            // 取出第二小的二叉树
            Node right = nodeList.get(nodeList.size() - 2);
            // 创建新的二叉树,并设置其子树
            Node parent = new Node(null, left.getWeight() + right.getWeight());
            // 设置左子树
            parent.left = left;
            // 设置右子树
            parent.right = right;
            nodeList.add(parent);
            // 删除原先的二叉树
            nodeList.remove(left);
            nodeList.remove(right);
        }
        return nodeList.get(0);
    }

    /**
     * 将字节数组创建转换成二叉树集合
     *
     * @param bytes
     * @return
     */
    private List<Node> createNodeList(byte[] bytes) {
        List<Node> nodeList = new ArrayList<>();
        Map<Byte, Integer> nodeMap = new HashMap<>();
        int count = 1;
        for (Byte key : bytes) {
            if (nodeMap.get(key) == null) {
                nodeMap.put(key, count);
            } else {
                nodeMap.put(key, nodeMap.get(key) + 1);
            }
        }
        // 将map中的key-value转换成node list
        for (Map.Entry<Byte, Integer> entry : nodeMap.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            nodeList.add(node);
        }
        return nodeList;
    }

}
