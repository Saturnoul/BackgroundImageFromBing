package com.saturn.background.tool;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @program: BackgroundImageFromBing
 * @description: The tool for downloading image from https:// Bing.com
 * @author: saturn
 * @create: 2020/05/06
 **/
public class BingImage {
    //Download image
    public static String download(String folder) {
        String imgDir = folder;
        File imgDirFile = new File(imgDir);
        if (!imgDirFile.exists()){
            imgDir = System.getProperty("user.home") + File.separator + "idea_bg";
            NotificationCenter.notice("Path " + folder + " doesn't exist, image stored in " + imgDir);
        }
        try {
            URL url = new URL("http://area.sinaapp.com/bingImg");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            String imgUrl = conn.getHeaderField("Location");

            url = new URL(imgUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            File file = new File(imgDir, "bing.jpg");
            OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            int size = 0;
            byte[] buf = new byte[1024];
            while((size = is.read(buf)) != -1){
                os.write(buf, 0, size);
            }
            is.close();
            os.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            NotificationCenter.notice("Failed to download image!");
            e.printStackTrace();
        }
        return null;
    }
}

