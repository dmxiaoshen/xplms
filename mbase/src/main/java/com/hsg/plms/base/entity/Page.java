package com.hsg.plms.base.entity;

import java.io.Serializable;

public class Page implements Serializable {

    /** */
    private static final long serialVersionUID = -8433015439219836136L;

    private int page;
    private int rows;
    private String sidx;
    private String sord;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

}
