package ch.bli.mez.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Keyword {
  
  public static Map<String, String> getKeywords(String url){
    Map<String, String> keywordsMap = new LinkedHashMap<String, String>();
    String[] pairs = url.split("&");
    for (String pair : pairs){
      int idx = pair.indexOf("=");
      keywordsMap.put(pair.substring(0, idx), pair.substring(idx+1));
    }
    return keywordsMap;
  }
  
}
