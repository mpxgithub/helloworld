package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.nio.channels.FileChannel;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String sayHello() {
        return "hello World";
    }

    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        fileChannelCopy("D:\\Dev_Env\\Ubuntu_17.04_x64.rar", "D:\\Dev_Env\\a.txt");
//        long end = System.currentTimeMillis();
//        System.out.println("文件管道方法用时：" + (end - start));
        long start1 = System.currentTimeMillis();
        copy("D:\\Dev_Env\\Ubuntu_17.04_x64.rar", "D:\\Dev_Env\\b.txt");
        long end1 = System.currentTimeMillis();
        System.out.println("普通方法用时：" + (end1 - start1));
    }

    public static void fileChannelCopy(String path, String newPath) {

        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(path);
            fo = new FileOutputStream(newPath);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void copy(String path, String newPath) {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(path));
            outputStream = new BufferedOutputStream(new FileOutputStream(newPath));
            byte[] buf = new byte[10240];
            int l;
            while ((l = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
