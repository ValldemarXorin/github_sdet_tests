package com.github_tst_sdet.model;

import com.github_tst_sdet.configuration.annotation.FieldName;
import com.github_tst_sdet.configuration.annotation.ResourcePath;

@ResourcePath("src/test/resources/config/browser.yaml")
public class Browser {
    private String browser;

    private boolean headless;

    private Window window;

    private Timeouts timeouts;

    private Remote remote;

    private boolean video;

    private boolean screenshots;


    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public boolean isHeadless() {
        return headless;
    }

    public void setHeadless(boolean headless) {
        this.headless = headless;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Timeouts getTimeouts() {
        return timeouts;
    }

    public void setTimeouts(Timeouts timeouts) {
        this.timeouts = timeouts;
    }

    public Remote getRemote() {
        return remote;
    }

    public void setRemote(Remote remote) {
        this.remote = remote;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public boolean isScreenshots() {
        return screenshots;
    }

    public void setScreenshots(boolean screenshots) {
        this.screenshots = screenshots;
    }


    @Override
    public String toString() {
        return "Browser{" +
                "browser='" + browser + '\'' +
                ", headless=" + headless +
                ", window=" + window +
                ", timeouts=" + timeouts +
                ", remote=" + remote +
                ", video=" + video +
                ", screenshots=" + screenshots +
                '}';
    }
}
