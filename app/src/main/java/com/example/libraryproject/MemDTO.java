package com.example.libraryproject;

import com.google.gson.annotations.SerializedName;

public class MemDTO {

    @SerializedName("Result")
    private String RESULT_CODE;
    @SerializedName("MemNo")
    private int memNo;
    @SerializedName("MemberId")
    private String memId;
    @SerializedName("MemberPw")
    private String memPw;
    private String memName;
    private Integer memPhone;
    @SerializedName("MemberFlag")
    private String memFlag;
    //string으로 바꿨오 우구우구 잘했오오오 우리 아가아 다시 해보까오?

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

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMemPw() {
        return memPw;
    }

    public void setMemPw(String memPw) {
        this.memPw = memPw;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public int getMemPhone() {
        return memPhone;
    }

    public void setMemPhone(int memPhone) {
        this.memPhone = memPhone;
    }

    public void setMemFlag(String memFlag) {
        this.memFlag = memFlag;
    }

    public String getMemFlag() {
        return memFlag;
    }



/*    @Override
    public String toString(){
        return "Member [memId=" + memId + ",memPw" + memPw+
                ", memName=" + memName + ", memPhone=" + memPhone + ", memFlag=" + memFlag + "]";
    }*/
}

