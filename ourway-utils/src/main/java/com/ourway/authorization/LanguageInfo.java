package com.ourway.authorization;

import com.ourway.base.utils.JsonUtil;

/**<p>方法 LanguageInfo :语言授权信息 <p>
*<p>说明:语言授权信息</p>
*<pre>
*@author CuiLiang
*@date 2017-07-17 10:12
</pre>
*/
public class LanguageInfo {
    String lanCode = "";
    String lanName = "";
    String lanTimeBegin = "";//语言的单独的某授权时间
    String lanTimeEnd = "";//语言的单独的授权结束时间
    String lanEndWarmingDate = "";//语言的单独的授权结束警告开始时间，区间为lanEndWarmingDate至lanTimeEnd进行警告提示
    String lanEndWarming = "";//语言的单独的授权结束警告期提示信息
    String lanEndError = "";//语言的单独的授权结束后的提示信息
    String lanMaxUser = "";//系统整体最大在线数
    String lanCipher = "";//语言单独签名值

    public LanguageInfo() {
    }

    public LanguageInfo(String lanCode, String lanName, String lanTimeBegin, String lanTimeEnd, String lanEndWarmingDate,
                        String lanEndWarming, String lanEndError, String lanMaxUser, String lanCipher) {
        this.lanCode = lanCode;
        this.lanName = lanName;
        this.lanTimeBegin = lanTimeBegin;
        this.lanTimeEnd = lanTimeEnd;
        this.lanEndWarmingDate = lanEndWarmingDate;
        this.lanEndWarming = lanEndWarming;
        this.lanEndError = lanEndError;
        this.lanMaxUser = lanMaxUser;
        this.lanCipher = lanCipher;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }

    public String getLanCode() {
        return lanCode;
    }

    public void setLanCode(String lanCode) {
        this.lanCode = lanCode;
    }

    public String getLanName() {
        return lanName;
    }

    public void setLanName(String lanName) {
        this.lanName = lanName;
    }

    public String getLanTimeBegin() {
        return lanTimeBegin;
    }

    public void setLanTimeBegin(String lanTimeBegin) {
        this.lanTimeBegin = lanTimeBegin;
    }

    public String getLanTimeEnd() {
        return lanTimeEnd;
    }

    public void setLanTimeEnd(String lanTimeEnd) {
        this.lanTimeEnd = lanTimeEnd;
    }

    public String getLanEndWarmingDate() {
        return lanEndWarmingDate;
    }

    public void setLanEndWarmingDate(String lanEndWarmingDate) {
        this.lanEndWarmingDate = lanEndWarmingDate;
    }

    public String getLanEndWarming() {
        return lanEndWarming;
    }

    public void setLanEndWarming(String lanEndWarming) {
        this.lanEndWarming = lanEndWarming;
    }

    public String getLanEndError() {
        return lanEndError;
    }

    public void setLanEndError(String lanEndError) {
        this.lanEndError = lanEndError;
    }

    public String getLanMaxUser() {
        return lanMaxUser;
    }

    public void setLanMaxUser(String lanMaxUser) {
        this.lanMaxUser = lanMaxUser;
    }

    public String getLanCipher() {
        return lanCipher;
    }

    public void setLanCipher(String lanCipher) {
        this.lanCipher = lanCipher;
    }
}
