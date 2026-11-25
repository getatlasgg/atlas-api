package be.esmay.atlas.api.drain;

/**
 * Keeps the players on this server while Atlas replaces it. Atlas moves them
 * once every hold is released, or stops the server with them at the deadline.
 */
public interface PlayerHold extends AutoCloseable {

    /**
     * Lets Atlas move the players. Releasing twice does nothing.
     */
    void release();

    /**
     * Same as {@link #release()}.
     */
    @Override
    default void close() {
        this.release();
    }
}
