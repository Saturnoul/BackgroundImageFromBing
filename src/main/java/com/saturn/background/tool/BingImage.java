package com.saturn.background.tool;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;

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
    public static String download() {
        String imgDir = System.getProperty("user.home") + File.separator + ".idea_bg";
        File imgDirFile = new File(imgDir);
        if (!imgDirFile.exists()){
            imgDirFile.mkdir();
            NotificationCenter.notice("Image stored in " + imgDir);
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
            byte[] buf = new byte[8192];
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

    public static void setBackground(){
        PropertiesComponent prop = PropertiesComponent.getInstance();
        String image = download();
        prop.setValue(IdeBackgroundUtil.EDITOR_PROP, image);
        prop.setValue(IdeBackgroundUtil.FRAME_PROP, image);

        NotificationCenter.notice("Background switched successfully");
    }

    public static void clearBackground() {
        PropertiesComponent prop = PropertiesComponent.getInstance();
        prop.setValue(IdeBackgroundUtil.EDITOR_PROP, null);
        prop.setValue(IdeBackgroundUtil.FRAME_PROP, null);
    }
}

