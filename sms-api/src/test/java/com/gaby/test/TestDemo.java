package com.gaby.test;

import com.gaby.util.HttpClient;
import org.junit.Test;

import java.io.IOException;

public class TestDemo {





    @Test
    public void fun1() throws IOException {
        String url = "http://api.weipaitang.com/app/v1.0/liveroom/add-room-msg-l";
        HttpClient httpClient = new HttpClient(url,null);
        httpClient.setHttps(false);
        httpClient.addParameter("type","0");
        httpClient.addParameter("roomId","152");
        httpClient.addParameter("content","666666");
        httpClient.setHeader("Cookie","userinfo_cookie=p1fI%2FnAVGABz9cIKvM20x5%2F0g%2F91nkdCXbOXfPNYnqsgNAneWmq3FPFSOXpYV3Ocd%2F0f%2BjyuktNiwvOiVpKZUjwalTLS1fBM6gFj3XYwGhAuU2A%2Fp8e6w7OvyGBG86pZ8XrzbcwQ%2BYwUurJ8qJLMcYXuPX%2B%2BaEA%2FG1KC%2FBY0Q%2Fikvr%2FTiokzhOH%2BrYAbE%2Fkw4I9QGB03Zyf1Q5kkeaLkP9DSmp%2FwV%2FG1nVBD%2FSc0GA35mqzGCQAptUJ7Zz%2FIC2WQtANy9Mr8wfb1FrMZXDfxSUYX8FAJ2sVVW2GQFPOJxxk%3D");
        httpClient.setHeader("Origin","app.weipaitang.com");
        httpClient.setHeader("User-Agent","NetType/NETWORK_WIFI Language/zh_CN WptMessenger/2.8.0 Channel/vivo");
        httpClient.setHeader("REFERER","app.weipaitang.com");
        httpClient.setHeader("Content-Type","application/x-www-form-urlencoded");
        //httpClient.setHeader("Content-Length","35");
        httpClient.setHeader("Connection","Keep-Alive");
        httpClient.setHeader("Accept-Encoding","gzip");
        httpClient.setHeader("Host","api.weipaitang.com");
        httpClient.post();
    }

    @Test
    public void fun2() throws IOException {
        String url = "http://api.weipaitang.com/app/v1.0/liveroom/active-l";
        HttpClient httpClient = new HttpClient(url,null);
        httpClient.setHttps(false);
        httpClient.addParameter("roomId","152");
        httpClient.setHeader("Cookie","userinfo_cookie=p1fI%2FnAVGABz9cIKvM20x5%2F0g%2F91nkdCXbOXfPNYnqsgNAneWmq3FPFSOXpYV3Ocd%2F0f%2BjyuktNiwvOiVpKZUjwalTLS1fBM6gFj3XYwGhAuU2A%2Fp8e6w7OvyGBG86pZ8XrzbcwQ%2BYwUurJ8qJLMcYXuPX%2B%2BaEA%2FG1KC%2FBY0Q%2Fikvr%2FTiokzhOH%2BrYAbE%2Fkw4I9QGB03Zyf1Q5kkeaLkP9DSmp%2FwV%2FG1nVBD%2FSc0GA35mqzGCQAptUJ7Zz%2FIC2WQtANy9Mr8wfb1FrMZXDfxSUYX8FAJ2sVVW2GQFPOJxxk%3D");
        httpClient.setHeader("Origin","app.weipaitang.com");
        httpClient.setHeader("REFERER","app.weipaitang.com");
        httpClient.setHeader("Content-Type","application/x-www-form-urlencoded");
       // httpClient.setHeader("Content-Length","10");
        httpClient.setHeader("User-Agent","NetType/NETWORK_WIFI Language/zh_CN WptMessenger/2.8.0 Channel/vivo");
        httpClient.setHeader("Connection","Keep-Alive");
        httpClient.setHeader("Accept-Encoding","gzip");
        httpClient.setHeader("Host","api.weipaitang.com");
        httpClient.post();
    }
    @Test
    public void fun0() throws IOException {
        String url = "http://webbi.weipaitang.com/ereport";
        HttpClient httpClient = new HttpClient(url, null);
        httpClient.setHttps(false);
        httpClient.setXmlParam("{\"data\":{\"liveData\":{\"type\":\"live-chat\"},\"subType\":\"live-chat\"},\"liveData\":{\"type\":\"live-chat\"},\"page\":{\"pageName\":\"[图片]https://w.weipaitang.com/live/room\",\"pos\":\"\",\"r\":\"menu_home-home_saleLive-liveHot\",\"sc\":\"vivo\"},\"requestData\":{\"androidId\":\"b85cd05f93f2508a\",\"appId\":\"com.weipaitang.wpt\",\"cVersion\":\"2.8.0\",\"deviceId\":\"869497039784457\",\"href\":\"[图片]https://w.weipaitang.com/live/room?r=menu_home-home_saleLive-liveHot&c=menu_home-home_saleLive-liveHot\",\"ip\":\"ipToBeFilled\",\"jPushRegId\":\"190e35f7e0619e44989\",\"mac\":\"9c:e8:2b:5d:37:e3\",\"oVersion\":\"8.1.0\",\"os\":\"android\",\"packageName\":\"vivo\",\"platform\":\"app\",\"referer\":\"[图片]https://w.weipaitang.com/live/channel?r=menu_home-home_saleLive&c=menu_home-home_saleLive\",\"reportVersion\":\"1.0.0\",\"screen\":\"1080_2280\",\"sessionId\":\"20190419230529_tvwzfjk506\",\"userAgent\":\"vivoX21A\",\"webType\":\"android\"},\"sc\":\"vivo\",\"time\":\"timeToBeFilled\",\"type\":\"click\",\"user\":{\"isNewUser\":0,\"usid\":\"6t3kmW1VDXA3+FLKJ/BsoWCPNXLHS9hxHeEO42i/lag=\",\"uuri\":\"1509052249elvF21\"},\"userinfoData\":{\"isNewUser\":0},\"usid\":\"6t3kmW1VDXA3+FLKJ/BsoWCPNXLHS9hxHeEO42i/lag=\",\"uuri\":\"1509052249elvF21\"}");
        httpClient.setHeader("Cookie","userinfo_cookie=p1fI%2FnAVGABz9cIKvM20x5%2F0g%2F91nkdCXbOXfPNYnqsgNAneWmq3FPFSOXpYV3Ocd%2F0f%2BjyuktNiwvOiVpKZUjwalTLS1fBM6gFj3XYwGhAuU2A%2Fp8e6w7OvyGBG86pZ8XrzbcwQ%2BYwUurJ8qJLMcYXuPX%2B%2BaEA%2FG1KC%2FBY0Q%2Fikvr%2FTiokzhOH%2BrYAbE%2Fkw4I9QGB03Zyf1Q5kkeaLkP9DSmp%2FwV%2FG1nVBD%2FSc0GA35mqzGCQAptUJ7Zz%2FIC2WQtANy9Mr8wfb1FrMZXDfxSUYX8FAJ2sVVW2GQFPOJxxk%3D");
        httpClient.setHeader("Origin","app.weipaitang.com");
        httpClient.setHeader("User-Agent","NetType/NETWORK_WIFI Language/zh_CN WptMessenger/2.8.0 Channel/vivo");
        httpClient.setHeader("REFERER","app.weipaitang.com");
        httpClient.setHeader("Content-Type","text/plain;charset=utf-8");
        // httpClient.setHeader("Content-Length","994");
        httpClient.setHeader("Connection","Keep-Alive");
        httpClient.setHeader("Accept-Encoding","gzip");
        httpClient.setHeader("Host","api.weipaitang.com");
        httpClient.post();
    }
}
