package be.esmay.atlas.api.event;

import java.util.function.Consumer;

/**
 * Delivers {@link ServerEvent}s to listeners, one at a time and in the order
 * they happened. A listener runs on Atlas's own thread, so it hands slow work
 * to the platform's scheduler.
 */
public interface Events {

    /**
     * Listens for one kind of event.
     *
     * @param type the kind, such as {@code ServerAdded.class}, or {@code ServerEvent.class} for all of them
     * @param listener receives each event of that kind
     * @param <E> the kind of event
     * @return the subscription, which ends when it is closed
     */
    <E extends ServerEvent> Subscription on(Class<E> type, Consumer<? super E> listener);
}
