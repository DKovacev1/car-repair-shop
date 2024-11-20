package hr.autorepair.common.utils;

public class StringUtil {

    public static String getSubstringBetween(String text, String start, String end){
        int startIndex = text.indexOf(start);
        if(startIndex == -1)
            return null;

        startIndex += start.length();

        int endIndex = text.indexOf(end, startIndex);
        if(endIndex == -1)
            return null;

        return text.substring(startIndex, endIndex);
    }

}
