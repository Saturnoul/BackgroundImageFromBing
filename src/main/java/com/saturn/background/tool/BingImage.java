package com.saturn.background.tool;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import com.saturn.background.setting.OpacitySettingState;

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
    public static final String BING_IMAGE_URL = "http://area.sinaapp.com/bingImg";
    //Download image
    public static String download() {
        String imgDir = System.getProperty("user.home") + File.separator + ".idea_bg";
        File imageFile = new File(imgDir, "bing.jpg");
        File imgDirFile = new File(imgDir);
        if (!imgDirFile.exists()){
            imgDirFile.mkdir();
            NotificationCenter.notice("Image stored in " + imgDir);
        }
        try {
            URL url = new URL(BING_IMAGE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            String imgUrl = conn.getHeaderField("Location");

            url = new URL(imgUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            OutputStream os = new BufferedOutputStream(new FileOutputStream(imageFile));
            int size = 0;
            byte[] buf = new byte[8192];
            while((size = is.read(buf)) != -1){
                os.write(buf, 0, size);
            }
            is.close();
            os.close();
            return imageFile.getAbsolutePath();
        } catch (IOException e) {
            NotificationCenter.notice("Failed to download image!");
            e.printStackTrace();
        }
        return imageFile.exists() ? imageFile.getAbsolutePath() : null;
    }

    public static void setBackground(){
        PropertiesComponent prop = PropertiesComponent.getInstance();
        String image = download();
        String opacity = String.valueOf(OpacitySettingState.loadState());
        String imageProp = String.format("%s,%s", image, opacity);
        prop.setValue(IdeBackgroundUtil.EDITOR_PROP, imageProp);
        prop.setValue(IdeBackgroundUtil.FRAME_PROP, imageProp);

        NotificationCenter.notice("Background switched successfully");
    }

    public static void clearBackground() {
        PropertiesComponent prop = PropertiesComponent.getInstance();
        prop.setValue(IdeBackgroundUtil.EDITOR_PROP, null);
        prop.setValue(IdeBackgroundUtil.FRAME_PROP, null);

    }
}

