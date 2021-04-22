package com.saturn.background;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.saturn.background.tool.BingImage;
import org.jetbrains.annotations.NotNull;

public class MyStartupActivity implements StartupActivity {
    @Override
    public void runActivity(@NotNull Project project) {
        BingImage.setBackground();
    }
}
