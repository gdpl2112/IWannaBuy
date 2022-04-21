package io.github.kloping.iwanna.buy.api.listener;

import io.github.kloping.iwanna.buy.api.Event;

/**
 * @author github.kloping
 */
public interface OnEventListener {
    /**
     * on event before
     *
     * @param event
     */
    void onEventBefore(Event event);

    /**
     * on event after
     *
     * @param event
     */
    void onEventAfter(Event event);
}
