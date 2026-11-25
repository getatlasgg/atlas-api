package be.esmay.atlas.api.drain;

/**
 * Hears when Atlas starts or stops replacing this server. Both calls run on
 * Atlas's own thread, before Atlas moves any player.
 */
public interface DrainListener {

    /**
     * Atlas started replacing this server. Take a {@link PlayerHold} here to
     * keep the players on this server until the replacement is ready for them.
     *
     * @param draining the replacement and the deadline
     */
    void draining(Draining draining);

    /**
     * Atlas stopped replacing this server, and it takes players again. A
     * plugin that gave up a lease for the handover acquires it again here.
     */
    default void cancelled() {
    }
}
