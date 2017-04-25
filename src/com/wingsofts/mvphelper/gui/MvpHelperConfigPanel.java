package com.wingsofts.mvphelper.gui;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.UnnamedConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.wingsofts.mvphelper.biz.config.MvpConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.ResourceBundle;

/**
 * @author DengChao
 * @since 2017/4/24
 */
@State(name = "MvpHelperConfig", storages = {@Storage("MvpHelperConfig.xml")})//try register a storage place
public class MvpHelperConfigPanel implements Configurable, MvpConfigurable {
    private static final String PREFIX = "MVP_HELPER_";
    private static final String KEY_HAS_SUFFIX = PREFIX + "KEY_HAS_SUFFIX";
    private static final String KEY_SUFFIX = PREFIX + "KEY_SUFFIX";
    private ResourceBundle string;//the static resource bundle
    private PropertiesComponent properties;//the properties object, used to save and load property.
    private String helloWorldModel;
    private String suffix;
    private JCheckBox ckEnableSuffix;
    private JTextField tfSuffixTemplate;
    private JTextArea taSuffixExample;
    private JLabel lFeedBack;
    private JLabel lOpenOnGitHub;
    private JPanel panel;
    private boolean hasSuffix;
    private boolean hasSuffixNewValue;

    /**
     * Constructor for Intellij Platform usage
     */
    @SuppressWarnings("unused")
    public MvpHelperConfigPanel(Project project) {
        this(project, true);
    }

    /**
     * Constructor for User usage
     *
     * @param project  the project witch need to save and load config
     * @param fullInit is all Swing object need to init listeners
     */
    public MvpHelperConfigPanel(Project project, boolean fullInit) {
        string = ResourceBundle.getBundle("string");
        properties = PropertiesComponent.getInstance(project);//always project level.
        helloWorldModel = string.getString("suffixExample");

        hasSuffixNewValue = hasSuffix = properties.getBoolean(KEY_HAS_SUFFIX);//they are the same when init
        suffix = hasSuffix ? properties.getValue(KEY_SUFFIX) : "";//init update

        if (!fullInit) {
            return;
        }

        ckEnableSuffix.addItemListener(event -> {
            hasSuffixNewValue = ckEnableSuffix.isSelected();//then user changed the new value
            tfSuffixTemplate.setFocusable(hasSuffixNewValue);
            tfSuffixTemplate.setEnabled(hasSuffixNewValue);

            //no matter enable or disable, just reset the suffix template
            tfSuffixTemplate.setText("");
            taSuffixExample.setText(helloWorldModel);
        });

        tfSuffixTemplate.addCaretListener((CaretEvent caretEvent) ->
                taSuffixExample.setText(helloWorldModel + tfSuffixTemplate.getText()));//dynamic update

        ckEnableSuffix.setSelected(hasSuffix);
        tfSuffixTemplate.setEnabled(hasSuffix);
        tfSuffixTemplate.setFocusable(hasSuffix);
        taSuffixExample.setText(helloWorldModel + suffix);

        lFeedBack.addMouseListener(new OnClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String feedBackUrl = string.getString("feedBackUrl");
                try {
                    Desktop.getDesktop().browse(URI.create(feedBackUrl));
                } catch (Exception exception) {
                    Messages.showErrorDialog(
                            "Oh..It seems we cannot open any browser on this platform automatically." +
                                    " But you could do it yourself:\n" +
                                    feedBackUrl, "Browse Error");
                    exception.printStackTrace();
                }
            }

        });

        lOpenOnGitHub.addMouseListener(new OnClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String gitHubUrl = string.getString("gitHubUrl");
                try {
                    Desktop.getDesktop().browse(URI.create(gitHubUrl));
                } catch (Exception exception) {
                    Messages.showErrorDialog(
                            "Oh..It seems we cannot open any browser on this platform automatically." +
                                    " But you could do it yourself:\n" +
                                    gitHubUrl, "Browse Error");
                    exception.printStackTrace();
                }
            }
        });
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Mvp Helper";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return panel;
    }

    @Override
    public boolean isModified() {
        return hasSuffix != hasSuffixNewValue || !suffix.equals(tfSuffixTemplate.getText());
    }

    @Override
    public void reset() {
        ckEnableSuffix.setSelected(hasSuffix);
        tfSuffixTemplate.setText(suffix);
        taSuffixExample.setText(helloWorldModel + suffix);

        properties.setValue(KEY_HAS_SUFFIX, hasSuffix);
        properties.setValue(KEY_SUFFIX, suffix);
    }

    @Override
    public void apply() throws ConfigurationException {
        String suffixNewValue = tfSuffixTemplate.getText();
        properties.setValue(KEY_HAS_SUFFIX, hasSuffixNewValue);
        properties.setValue(KEY_SUFFIX, suffixNewValue);

        hasSuffix = hasSuffixNewValue;//update isModified()
        suffix = suffixNewValue;
    }

    /**
     * Override this to avoid AbstractMethodException,
     * for the IDEA plugin dev-platform use Java 8's feature: default interface
     *
     * @see UnnamedConfigurable#disposeUIResources()
     */
    @Override
    public void disposeUIResources() {
        //noinspection BoundFieldAssignment
        panel = null;
    }

    /**
     * Only available in MvpHelperConfigPanel(project,false)
     */
    @Override
    public String getSuffix() {
        return suffix;
    }

    /**
     * Only available in MvpHelperConfigPanel(project,false)
     */
    @Override
    public boolean hasSuffix() {
        return hasSuffix;
    }

    private abstract class OnClickListener implements MouseInputListener {
        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}
