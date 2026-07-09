package com.github_tst_sdet.model;

public class Timeouts {

    private int timeout;

    private int pageLoad;

    private int polling;


    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getPageLoad() {
        return pageLoad;
    }

    public void setPageLoad(int pageLoad) {
        this.pageLoad = pageLoad;
    }

    public int getPolling() {
        return polling;
    }

    public void setPolling(int polling) {
        this.polling = polling;
    }


    @Override
    public String toString() {
        return "Timeouts{" +
                "timeout=" + timeout +
                ", pageLoad=" + pageLoad +
                ", polling=" + polling +
                '}';
    }
}
