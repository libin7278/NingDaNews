package com.wudian.doudou.ningdanews.utils;

import com.google.gson.Gson;
import com.wudian.doudou.ningdanews.domain.ChatMessage;
import com.wudian.doudou.ningdanews.domain.Resule;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Created by doudou on 16/2/21.
 */
public class HttpUtils {

    private static final String URL ="http://www.tuling123.com/openapi/api";
    private static final String API_KEY="60eac0749164c426e9dd18ca41b8ad88";

    /**
     * 发送一个消息,得到返回信息
     * @param meg
     * @return
     */
    public static ChatMessage sendMessage(String meg){
        ChatMessage chatMessage = new ChatMessage();

        String jsonResult = doGet(meg);

        Gson gson = new Gson();
        Resule resule = null;
        try {
            resule = gson.fromJson(jsonResult, Resule.class);
            chatMessage.setMsg(resule.getText());
        } catch (Exception e) {
            chatMessage.setMsg("服务器异常,稍后再试");
        }

        chatMessage.setDate(new Date());
        chatMessage.setType(ChatMessage.Type.INCOMING);
        return chatMessage;
    }

    public static String doGet(String msg){
        String result ="";

        InputStream is = null;
        ByteArrayOutputStream baos=null;

        String url = setParams(msg);

        try {
            java.net.URL urlNet = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) urlNet.openConnection();
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            urlConnection.setRequestMethod("GET");

            is = urlConnection.getInputStream();
            int len = -1;
            byte[] buf = new byte[128];
            baos = new ByteArrayOutputStream();

            while((len = is.read(buf))!= -1){
                baos.write(buf,0,len);
            }


            // 清除缓冲区
            baos.flush();
            result = new String(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    private static String setParams(String msg) {
        String url = URL + "?key=" +API_KEY +"&info=" +msg;
        return url;
    }
}
