package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.nio.channels.FileChannel;

@Controller
public class HelloController {

  

    public static void fileChannelCopy1(String sourcePath, String newPath) {

        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(path);
            fo = new FileOutputStream(newPath);
            in = fi.getChannel();
            out = fo.getChannel();
            in.transferTo(0, in.size(), out);
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

    public static void copy1(String sourcePath, String newPath) {

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
