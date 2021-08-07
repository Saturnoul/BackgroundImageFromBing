package com.saturn.background.setting;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.saturn.background.tool.BingImage;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class OpacitySetting implements Configurable{
    private OpacitySettingComponent opacitySettingComponent;

    public OpacitySetting() {
        super();
    }
    @Override
    public String getDisplayName() {
        return "Background Opacity Setting";
    }

    @Override
    public @Nullable JComponent createComponent() {
        opacitySettingComponent = new OpacitySettingComponent();
        return opacitySettingComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        return OpacitySettingState.loadState() != opacitySettingComponent.getOpacity();
    }

    @Override
    public void apply() throws ConfigurationException {
        if(isModified()) {
            OpacitySettingState.storeState(opacitySettingComponent.getOpacity());
            BingImage.setBackground();
        }
    }
}
