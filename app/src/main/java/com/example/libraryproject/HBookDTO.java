package com.example.libraryproject;

import com.google.gson.annotations.SerializedName;

public class HBookDTO {
    @SerializedName("Result")
    private String RESULT_CODE;
    @SerializedName("HTitle")
    private String hbookTitle;
    @SerializedName("HAuthor")
    private String hbookAuthor;
    @SerializedName("HPublish")
    private String hbookPublish;
    @SerializedName("HNo")
    private int hbookNo;
    private int memNo;

    public String getHbookPublish() {
        return hbookPublish;
    }

    public void setHbookPublish(String hbookPublish) {
        this.hbookPublish = hbookPublish;
    }

    public String getHbookTitle() {
        return hbookTitle;
    }

    public void setHbookTitle(String hbookTitle) {
        this.hbookTitle = hbookTitle;
    }

    public String getHbookAuthor() {
        return hbookAuthor;
    }

    public void setHbookAuthor(String hbookAuthor) {
        this.hbookAuthor = hbookAuthor;
    }


    public int getHbookNo() {
        return hbookNo;
    }

    public void setHbookNo(int hbookNo) {
        this.hbookNo = hbookNo;
    }

    public String getRESULT_CODE() {
        return RESULT_CODE;
    }

    public void setRESULT_CODE(String RESULT_CODE) {
        this.RESULT_CODE = RESULT_CODE;
    }


    public int getMemNo() {
        return memNo;
    }

    public void setMemNo(int memNo) {

        this.memNo = memNo;
    }
}
