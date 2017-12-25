package com.ourway.web.api.test;

import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.service.DicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Unit test for simple App.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/ApplicationContext.xml")
public class AppTest {
    @Autowired
    private DicService dicSer;


    @Test
    public void testAd() {
        OurwaySysDic dic = new OurwaySysDic();
        dic.setFid(3);
        dicSer.saveOrUpdate(dic);
        System.out.print("success:*******");

    }

//    public void testQueryListByCode(){
//        adviceSer.queryListByCode("123");
//
//
//    }
//    @org.junit.Test
//    public void testAddCard(){
//        AppBizCardVo cardVo = new AppBizCardVo();
////        cardVo.setCode();
//        cardVo.setExplains("hashaahah");
//        cardVo.setPhotoFront("sdas");
//        cardVo.setPhotoBack("Dasda");
//        cardVo.setRealNumber("1213133");
//        cardSer.addCard("sdfds",cardVo);
//
//    }
//    @org.junit.Test
//    public void testUpdateCard(){
//        AppBizCardVo cardVo = new AppBizCardVo();
////        cardVo.setCode();
//        cardVo.setExplains("sfsfsfs");
//        cardVo.setCode("7919CF334C0F46F0AE5010CF70E085EF");
//        cardVo.setPhotoFront("ssfsfsdas");
//        cardVo.setPhotoBack("Dasfsfsfsda");
////        cardVo.setRealNumber("1213133");
//        cardSer.updateCard(cardVo);
//    }
//
//    @org.junit.Test
//    public void testDeleteCard(){
//        cardSer.removeCard("7919CF334C0F46F0AE5010CF70E085EF");
//    }
//    @org.junit.Test
//    public void testAdv(){
//        List<AppBizAd>  list = adSer.getAll();
//        System.out.println("list");
//    }
//
//    @org.junit.Test
//    public void testSendMessage(){
//        AppBizMessageVo messageVo = new AppBizMessageVo();
//        messageVo.setCode("2");
//        messageVo.setSubmitTime(new Date());
//        messageVo.setPicture("sdasdasd");
//        messageVo.setContent("dsdsds");
//        messageVo.setTitle("666666");
//        messageSer.saveAndPushMessage(messageVo,"66");
//    }
//    @org.junit.Test
//    public void testMessageList(){
//
//        List<AppBizMessageVo> list = messageSer.queryAllByCode("2");
//        System.out.println(list);
//    }
//
//    @org.junit.Test
//    public void testDealOrderlList(){
//        List<AppBizConsumeVo> list = shopSer.getUserRecordInShop("sdfds","2");
//        System.out.println(list);
//    }


}

