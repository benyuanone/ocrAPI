## 使用说明
### com.ourway.aliyun下是汉王云的文字识别的示例
### com.ourway.baiduapi下是百度文字识别的部分接口：
:BaiDuApiInfo类下的信息自己去百度开发者中心申请应用，会给你相关信息，以下方法需要先执行getToKen获取密钥【一个月有效】后传如百度识别url才能执行
###1、身份证识别 com.ourway.baiduapi.action.BaiDuApi.idCardDiscriminate
###2、驾驶证识别 com.ourway.baiduapi.action.BaiDuApi.driverDiscriminate
###3、行驶证识别 com.ourway.baiduapi.action.BaiDuApi.vechiceDiscriminate
###4、企业营业执照识别 com.ourway.baiduapi.action.BaiDuApi.businessDiscriminate
###5、通用文字识别 com.ourway.baiduapi.action.BaiDuApi.generalDiscriminate
###6、通用接口，需要自己传入官网的接口地址，参数，url，阅读源码后应该会用。


#### 附 : jar下是一个打包好的加包（不包含aliyun的东西），可以传到自己的nexus服务器使用