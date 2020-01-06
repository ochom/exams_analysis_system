/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.jtattoo.plaf.DecorationHelper;
import javax.swing.UIManager;

/**
 *
 * @author Ritch
 */
public class Launcher {
    ConfigFileClass configClass;
    public static void main(String[] args) {
        try {
            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Default", "", "acme");
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        DecorationHelper.decorateWindows(false);
        new Launcher().RunLaunch();
    }

    private void RunLaunch() {
        try {
            configClass = new ConfigFileClass();
            configClass.CreatePreferences();

            if (configClass.isNewInstallation()) {
                configClass.setTrialDeadline();
                new SystemInitialization().run();
                return;
            }

            if (!configClass.isNewInstallation() && configClass.AgreedToTerms()) {
                new SystemInitialization().run();
                return;
            }

            if (!configClass.isNewInstallation() && !configClass.AgreedToTerms()) {
                new UpdateNotice().setVisible(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
