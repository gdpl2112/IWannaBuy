package io.github.Kloping.iwanna.buy.impl.saver;

import io.github.Kloping.file.FileUtils;
import io.github.Kloping.serialize.HMLObject;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * @author github.Kloping
 */
public class FileHmlSaver<T> extends FileSaver<T> {
    public FileHmlSaver(File file) {
        super(file);
    }

    @Override
    public T apply(T t) {
        FileUtils.putStringInFile(HMLObject.toHMLString(t), file);
        Logger.getLogger(this.getClass()).info(t.getClass().getSimpleName() + " apply");
        return t;
    }
}
