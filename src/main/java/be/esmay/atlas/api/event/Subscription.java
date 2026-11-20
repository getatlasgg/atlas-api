package be.esmay.atlas.api.event;

/**
 * A listener registered with {@link Events}.
 */
public interface Subscription extends AutoCloseable {

    /**
     * Stops delivering events to the listener. Closing twice does nothing.
     */
    @Override
    void close();
}
