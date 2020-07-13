package com.example.libraryproject;

import com.google.gson.annotations.SerializedName;

public class BoardDTO {

    @SerializedName("BoardNo")
    private String boardNo;
    @SerializedName("BoardTitle")
    private String boardTitle;
    @SerializedName("BoardContent")
    private String boardContent;
    @SerializedName("MemNo")
    private String memNo;
    @SerializedName("BoardBTitle")
    private String boardBookTitle;
    @SerializedName("MemFlag")
    private String memFlag;
    @SerializedName("MemName")
    private String memName;
    @SerializedName("Result")
    private String RESULT_CODE;
    @SerializedName("BoardFlag")
    private  String boardFlag;

    public String getBoardFlag() {
        return boardFlag;
    }

    public void setBoardFlag(String boardFlag) {
        this.boardFlag = boardFlag;
    }

    public String getRESULT_CODE() {
        return RESULT_CODE;
    }

    public void setRESULT_CODE(String RESULT_CODE) {
        this.RESULT_CODE = RESULT_CODE;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getMemFlag() {
        return memFlag;
    }

    public void setMemFlag(String memFlag) {
        this.memFlag = memFlag;
    }

    public String getBoardBookTitle() {
        return boardBookTitle;
    }

    public void setBoardBookTitle(String boardBookTitle) {
        this.boardBookTitle = boardBookTitle;
    }

    public String getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(String boardNo) {
        this.boardNo = boardNo;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public String getMemNo() {
        return memNo;
    }

    public void setMemNo(String memNo) {
        this.memNo = memNo;
    }
}
