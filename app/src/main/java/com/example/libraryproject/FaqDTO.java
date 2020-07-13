package com.example.libraryproject;

import com.google.gson.annotations.SerializedName;

public class FaqDTO {
    @SerializedName("FNo")
    private String FaqNo;
    @SerializedName("FTitle")
    private String FaqTitle;
    @SerializedName("FContent")
    private String FaqContent;
    @SerializedName("MemNo")
    private String memNo;
    private String memFlag;
    @SerializedName("Result")
    private String RESULT_CODE;
    @SerializedName("FFlag")
    private String FaqFlag;

    public String getFaqFlag() {
        return FaqFlag;
    }

    public void setFaqFlag(String faqFlag) {
        FaqFlag = faqFlag;
    }

    public String getRESULT_CODE() {
        return RESULT_CODE;
    }

    public void setRESULT_CODE(String RESULT_CODE) {
        this.RESULT_CODE = RESULT_CODE;
    }

    public String getMemFlag() {
        return memFlag;
    }

    public void setMemFlag(String memFlag) {
        this.memFlag = memFlag;
    }

    public String getFaqNo() {
        return FaqNo;
    }

    public void setFaqNo(String faqNo) {
        FaqNo = faqNo;
    }

    public String getFaqTitle() {
        return FaqTitle;
    }

    public void setFaqTitle(String faqTitle) {
        FaqTitle = faqTitle;
    }

    public String getFaqContent() {
        return FaqContent;
    }

    public void setFaqContent(String faqContent) {
        FaqContent = faqContent;
    }

    public String getMemNo() {
        return memNo;
    }

    public void setMemNo(String memNo) {
        this.memNo = memNo;
    }
}
