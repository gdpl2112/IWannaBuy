package io.github.Kloping.iwanna.buy.impl.saver;

import io.github.Kloping.iwanna.buy.api.Saver;

import java.io.File;

/**
 * @author github.Kloping
 */
public abstract class FileSaver<T> implements Saver<T> {
    protected File file;

    public FileSaver(File file) {
        this.file = file;
    }
}
