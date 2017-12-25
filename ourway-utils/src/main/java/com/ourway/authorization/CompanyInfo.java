package com.ourway.authorization;

import com.ourway.base.utils.JsonUtil;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

/**<p>方法 CompanyInfo : 授权公司信息<p>
*<p>说明:TODO</p>
*<pre>
*@author CuiLiang
*@date 2017-07-03 11:52
</pre>
*/
public class CompanyInfo {
    String compCode = "";
    String compName = "";
    String compTimeBegin = "";//集团的单独的某授权时间
    String compTimeEnd = "";//集团的单独的授权结束时间
    String compEndWarmingDate = "";//集团的单独的授权结束警告开始时间，区间为compEndWarmingDate至compTimeEnd进行警告提示
    String compEndWarming = "";//集团的单独的授权结束警告期提示信息
    String compEndError = "";//集团的单独的授权结束后的提示信息
    String compMaxUser = "";//系统整体最大在线数
    String compNo = "";//集团单独的授权用户数
    List<LanguageInfo> languageInfoList = new ArrayList<>();
    public String compCipher = "";//集团信息单独签名值

    public CompanyInfo() {
    }

    public CompanyInfo(String compCode, String compName, String compTimeBegin, String compTimeEnd, String compEndWarmingDate,
                       String compEndWarming, String compEndError, String compMaxUser, String compNo, List<LanguageInfo> languageInfoList,
                       String compCipher) {
        this.compCode = compCode;
        this.compName = compName;
        this.compTimeBegin = compTimeBegin;
        this.compTimeEnd = compTimeEnd;
        this.compEndWarmingDate = compEndWarmingDate;
        this.compEndWarming = compEndWarming;
        this.compEndError = compEndError;
        this.compMaxUser = compMaxUser;
        this.compNo = compNo;
        this.languageInfoList = languageInfoList;
        this.compCipher = compCipher;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);

    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompTimeBegin() {
        return compTimeBegin;
    }

    public void setCompTimeBegin(String compTimeBegin) {
        this.compTimeBegin = compTimeBegin;
    }

    public String getCompTimeEnd() {
        return compTimeEnd;
    }

    public void setCompTimeEnd(String compTimeEnd) {
        this.compTimeEnd = compTimeEnd;
    }

    public String getCompEndWarmingDate() {
        return compEndWarmingDate;
    }

    public void setCompEndWarmingDate(String compEndWarmingDate) {
        this.compEndWarmingDate = compEndWarmingDate;
    }

    public String getCompEndWarming() {
        return compEndWarming;
    }

    public void setCompEndWarming(String compEndWarming) {
        this.compEndWarming = compEndWarming;
    }

    public String getCompEndError() {
        return compEndError;
    }

    public void setCompEndError(String compEndError) {
        this.compEndError = compEndError;
    }

    public String getCompMaxUser() {
        return compMaxUser;
    }

    public void setCompMaxUser(String compMaxUser) {
        this.compMaxUser = compMaxUser;
    }

    public String getCompNo() {
        return compNo;
    }

    public void setCompNo(String compNo) {
        this.compNo = compNo;
    }

    public List<LanguageInfo> getLanguageInfoList() {
        return languageInfoList;
    }

    public void setLanguageInfoList(List<LanguageInfo> languageInfoList) {
        this.languageInfoList = languageInfoList;
    }

    public String getCompCipher() {
        return compCipher;
    }

    public void setCompCipher(String compCipher) {
        this.compCipher = compCipher;
    }
}
