package com.example.libraryproject;

import com.google.gson.annotations.SerializedName;

public class BookDTO {


    @SerializedName("Result")
    private String RESULT_CODE;
    @SerializedName("BNo")
    private String BookNo;
    @SerializedName("BTitle")
    private String bookTitle;
    @SerializedName("BAuthor")
    private String bookAuthor;
    @SerializedName("BPublish")
    private String bookPublish;
    @SerializedName("BDate")
    private String BDate;
    @SerializedName("BFlag")
    private  String bookFlag; //대출가능 대출중 예약?
    @SerializedName("MemNo")
    private String memNo;
    @SerializedName("BRent")
    private String BRent;//빌린날짜
    @SerializedName("BReturn")
    private String BookReturn;//반납날짜
    @SerializedName("EFlag")
    private String extendFlag;//연장
    @SerializedName("RFlag")
    private  String returnFlag;
    @SerializedName("RMemNo")
    private String rMemNo;

    public String getReturnFlag() {
        return returnFlag;
    }

    public void setReturnFlag(String returnFlag) {
        this.returnFlag = returnFlag;
    }

    public String getrMemNo() {
        return rMemNo;
    }

    public void setrMemNo(String rMemNo) {
        this.rMemNo = rMemNo;
    }

    public String getExtendFlag() {
        return extendFlag;
    }

    public void setExtendFlag(String extendFlag) {
        this.extendFlag = extendFlag;
    }

    public String getBookReturn() {
        return BookReturn;
    }

    public void setBookReturn(String bookReturn) {
        BookReturn = bookReturn;
    }

    public String getBookNo() {
        return BookNo;
    }
    public void setBookNo(String bookNo) {
        BookNo = bookNo;
    }

    public String getBRent() {
        return BRent;
    }

    public void setBRent(String BRent) {
        this.BRent = BRent;
    }

    public String getRESULT_CODE() {
        return RESULT_CODE;
    }

    public void setRESULT_CODE(String RESULT_CODE) {
        this.RESULT_CODE = RESULT_CODE;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;

    }

    public String getBookPublish() {
        return bookPublish;
    }

    public void setBookPublish(String bookPublish) {
        this.bookPublish = bookPublish;
    }

    public String getBDate() {
        return BDate;
    }

    public void setBDate(String BDate) {
        this.BDate = BDate;
    }

    public String getBookFlag() {
        return bookFlag;
    }

    public void setBookFlag(String bookFlag) {
        this.bookFlag = bookFlag;
    }

    public String getMemNo() {
        return memNo;
    }

    public void setMemNo(String memNo) {
        this.memNo = memNo;
    }
}
