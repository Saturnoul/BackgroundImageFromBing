package com.saturn.background.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.saturn.background.tool.BingImage;

public class SetBackground extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        BingImage.setBackground();
    }
}
