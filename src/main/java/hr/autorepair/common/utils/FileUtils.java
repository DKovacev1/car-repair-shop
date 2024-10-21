package hr.autorepair.common.utils;

import java.io.InputStream;

public class FileUtils {

    public static InputStream getFile(String path){
        return FileUtils.class.getResourceAsStream(path);
    }

}

