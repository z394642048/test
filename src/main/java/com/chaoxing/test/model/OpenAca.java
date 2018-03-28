/**
 *
 */
package com.chaoxing.test.model;

public class OpenAca {


    //columns START
    /**
     * id   db_column: id
     */
    private Integer id;
    /**
     * 学院名称   db_column: aca_name
     */
    private String acaName;
    /**
     * openTime   db_column: open_time
     */
    private java.util.Date openTime;
    /**
     * 1 可用 0 不可用   db_column: status
     */
    private Integer status;
    /**
     * 该学院所属的机构id   db_column: fid
     */
    private String fid;
    //columns END

    public OpenAca() {
    }

    public OpenAca( Integer id) {
        this.id = id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return this.id;
    }

    public void setAcaName(String value) {
        this.acaName = value;
    }

    public String getAcaName() {
        return this.acaName;
    }

    public void setOpenTime(java.util.Date value) {
        this.openTime = value;
    }

    public java.util.Date getOpenTime() {
        return this.openTime;
    }

    public void setStatus(Integer value) {
        this.status = value;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setFid(String value) {
        this.fid = value;
    }

    public String getFid() {
        return this.fid;
    }


}

