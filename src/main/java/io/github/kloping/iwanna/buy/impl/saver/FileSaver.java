package io.github.kloping.iwanna.buy.impl.saver;

import io.github.kloping.iwanna.buy.api.Saver;

import java.io.File;

/**
 * @author github.kloping
 */
public abstract class FileSaver<T> implements Saver<T> {
    protected File file;

    public FileSaver(File file) {
        this.file = file;
    }
}
