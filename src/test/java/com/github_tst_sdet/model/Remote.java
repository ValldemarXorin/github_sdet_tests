package com.github_tst_sdet.model;

public class Remote {

    private boolean enabled;

    private String url;


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "Remote{" +
                "enabled=" + enabled +
                ", url='" + url + '\'' +
                '}';
    }
}