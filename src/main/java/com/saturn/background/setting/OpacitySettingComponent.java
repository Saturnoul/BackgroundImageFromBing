package com.saturn.background.setting;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OpacitySettingComponent {
    private JSpinner opacitySpinner;
    private JPanel panel;
    private JLabel opacityLabel;
    private JLabel percentage;

    private static int DEFAULT_OPACITY = 10;

    public OpacitySettingComponent() {
        int initialValue = OpacitySettingState.loadState();
        SpinnerNumberModel numberModel = new SpinnerNumberModel(initialValue, 0, 100, 1);
        opacitySpinner.setModel(numberModel);
    }

    public JPanel getPanel() {
        return panel;
    }

    public int getOpacity() {
       return (int) opacitySpinner.getValue();
    }

    public void reset() {
        opacitySpinner.setValue(DEFAULT_OPACITY);
    }
}
