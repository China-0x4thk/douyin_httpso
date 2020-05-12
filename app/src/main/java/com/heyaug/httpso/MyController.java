package com.heyaug.httpso;

import android.util.Base64;
import com.douyin.gorgon;
import com.yanzhenjie.andserver.annotation.*;
import com.yanzhenjie.andserver.framework.body.JsonBody;
import com.yanzhenjie.andserver.http.HttpResponse;
import com.yixia.utils.TinyEncode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class MyController {
    private static HashMap<String, String> JsonObjectToHashMap(JSONObject jsonObj) throws JSONException {
        HashMap<String, String> data = new HashMap<>();
        Iterator<String> it = jsonObj.keys();
        while (it.hasNext()) {
            String key = it.next();
            String value = jsonObj.get(key).toString();
            data.put(key, value);
        }
        return data;
    }

    @GetMapping("/test")
    void postSign(HttpResponse response) {
        Map<String, String> signMap = new HashMap<>();
        signMap.put("signature", "heyaug");
        JSONObject jsonObject = new JSONObject(signMap);
        response.setBody(new JsonBody(jsonObject));
    }

    @ResponseBody
    @PostMapping("/gorgon")
    String getNewGogon(@RequestBody String body) {
        try {
            JSONObject bd = new JSONObject(body);
            String url = (String) bd.get("url");
            JSONObject headers = (JSONObject) bd.get("headers");
            return gorgon.getGorgon(url, JsonObjectToHashMap(headers));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @ResponseBody
    @PostMapping("/url")
    String getNewGogonUrl(@RequestBody String body) {
        return gorgon.getGorgon(body);
    }

    @ResponseBody
    @PostMapping("/decode")
    String mPai(@RequestBody String body) {
        try {
            byte[] data = Base64.decode(body, Base64.DEFAULT);
            return TinyEncode.DecodeResult(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
