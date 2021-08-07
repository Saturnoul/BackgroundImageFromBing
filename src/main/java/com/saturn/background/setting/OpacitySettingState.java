package com.saturn.background.setting;

import com.intellij.ide.util.PropertiesComponent;

public class OpacitySettingState {
    private static String OPACITY_ID = "com.saturn.background.opacity";
    private static int DEFAULT_OPACITY = 10;
    public static int loadState() {
        PropertiesComponent pc = PropertiesComponent.getInstance();
        return pc.getInt(OPACITY_ID, DEFAULT_OPACITY);
    }

    public static void storeState(int opacity) {
        PropertiesComponent pc = PropertiesComponent.getInstance();
        pc.setValue(OPACITY_ID, String.valueOf(opacity));
    }

    public static void reset() {
        PropertiesComponent pc = PropertiesComponent.getInstance();
        pc.setValue(OPACITY_ID, String.valueOf(DEFAULT_OPACITY));
    }
}
