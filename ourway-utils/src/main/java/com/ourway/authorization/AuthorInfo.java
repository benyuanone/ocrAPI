package com.ourway.authorization;

import com.ourway.base.utils.JsonUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**<p>方法 AuthorInfo : 各种授权信息类目<p>
*<p>说明:TODO</p>
*<pre>
*@author CuiLiang
*@date 2017-07-03 11:46
</pre>
*/
public class AuthorInfo {
    String sysCode ;//集团编号，一般不用
    String sysName ;//集团名称,一般不用
    String sysNo = "";//集团总体授权用户数，一般不用
    String sysTimeBegin = "";//系统授权时间
    String sysTimeEnd = "";//系统授权结束时间
    String sysEndWarmingDate = "";//系统授权结束警告开始时间，区间为sysEndWarmingDate至sysTimeEnd进行警告提示
    String sysEndWarming = "";//系统授权结束警告期提示信息
    String sysEndError = "";//系统授权结束后的提示信息
    String sysMaxUser = "";//系统整体最大在线数
    List<CompanyInfo> companyInfoList = new ArrayList<>();//集团公司独立授权信息
    List<LanguageInfo> languageInfos = new ArrayList<>();//集团语言独立授权信息
    String sysCipher = "";//整体信息签名

    public AuthorInfo() {
    }

    public AuthorInfo(String sysCode, String sysName, String sysNo, String sysTimeBegin, String sysTimeEnd, String sysEndWarmingDate,
                      String sysEndWarming, String sysEndError, String sysMaxUser, List<CompanyInfo> companyInfoList,
                      List<LanguageInfo> languageInfos) {
        this.sysCode = sysCode;
        this.sysName = sysName;
        this.sysNo = sysNo;
        this.sysTimeBegin = sysTimeBegin;
        this.sysTimeEnd = sysTimeEnd;
        this.sysEndWarmingDate = sysEndWarmingDate;
        this.sysEndWarming = sysEndWarming;
        this.sysEndError = sysEndError;
        this.sysMaxUser = sysMaxUser;
        this.companyInfoList = companyInfoList;
        this.languageInfos = languageInfos;
    }
    public void clearCiphers(){
        this.sysCipher = "";
        for(CompanyInfo c:companyInfoList){
            c.setCompCipher("");
        }
        for(LanguageInfo l: languageInfos){
           l.setLanCipher("");
        }
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }

    public String getSysTimeBegin() {
        return sysTimeBegin;
    }

    public void setSysTimeBegin(String sysTimeBegin) {
        this.sysTimeBegin = sysTimeBegin;
    }

    public String getSysTimeEnd() {
        return sysTimeEnd;
    }

    public void setSysTimeEnd(String sysTimeEnd) {
        this.sysTimeEnd = sysTimeEnd;
    }

    public String getSysEndWarmingDate() {
        return sysEndWarmingDate;
    }

    public void setSysEndWarmingDate(String sysEndWarmingDate) {
        this.sysEndWarmingDate = sysEndWarmingDate;
    }

    public String getSysEndWarming() {
        return sysEndWarming;
    }

    public void setSysEndWarming(String sysEndWarming) {
        this.sysEndWarming = sysEndWarming;
    }

    public String getSysEndError() {
        return sysEndError;
    }

    public void setSysEndError(String sysEndError) {
        this.sysEndError = sysEndError;
    }

    public String getSysMaxUser() {
        return sysMaxUser;
    }

    public void setSysMaxUser(String sysMaxUser) {
        this.sysMaxUser = sysMaxUser;
    }

    public List<CompanyInfo> getCompanyInfoList() {
        return companyInfoList;
    }

    public void setCompanyInfoList(List<CompanyInfo> companyInfoList) {
        this.companyInfoList = companyInfoList;
    }

    public List<LanguageInfo> getLanguageInfos() {
        return languageInfos;
    }

    public void setLanguageInfos(List<LanguageInfo> languageInfos) {
        this.languageInfos = languageInfos;
    }

    public String getSysCipher() {
        return sysCipher;
    }

    public void setSysCipher(String sysCipher) {
        this.sysCipher = sysCipher;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysNo() {
        return sysNo;
    }

    public void setSysNo(String sysNo) {
        this.sysNo = sysNo;
    }


}
