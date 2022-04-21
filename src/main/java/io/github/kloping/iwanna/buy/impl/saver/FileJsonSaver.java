package io.github.Kloping.iwanna.buy.impl.saver;

import com.alibaba.fastjson.JSON;
import io.github.Kloping.file.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * @author github.Kloping
 */
public class FileJsonSaver<T> extends FileSaver<T> {
    public FileJsonSaver(File file) {
        super(file);
    }

    @Override
    public T apply(T t) {
        FileUtils.putStringInFile(JSON.toJSONString(t), file);
        Logger.getLogger(this.getClass()).info(t.getClass().getSimpleName() + " apply");
        return t;
    }
}
