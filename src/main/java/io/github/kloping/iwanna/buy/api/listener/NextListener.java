package io.github.kloping.iwanna.buy.api.listener;

import io.github.kloping.iwanna.buy.impl.Sys;

/**
 * @author github.kloping
 */
public interface NextListener {
    /**
     * on next after called
     *
     * @param sys
     */
    void onNexted(Sys sys);

    /**
     * on next before
     *
     * @param sys
     */
    void onNextBefore(Sys sys);

}
