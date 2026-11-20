package be.esmay.atlas.api.server;

import java.util.Optional;

/**
 * This server, as Atlas knows it.
 */
public interface LocalServer {

    /**
     * This server's id. The worker sends it when it connects, so it is empty
     * until then.
     *
     * @return the id, or empty before the worker first connected
     */
    Optional<String> id();

    /**
     * Whether the Atlas worker on this node is connected right now.
     *
     * @return true while the worker is connected
     */
    boolean connected();

    /**
     * The metadata this server publishes to the network.
     *
     * @return this server's metadata
     */
    ServerMetadata metadata();

    /**
     * Where this server is in its game loop, read from its metadata.
     *
     * @return the phase, {@link GamePhase#WAITING} until one is set
     */
    default GamePhase phase() {
        return GamePhase.of(this.metadata().all().get(ServerMetadata.PHASE));
    }

    /**
     * Sets where this server is in its game loop.
     *
     * @param phase the new phase
     */
    default void phase(GamePhase phase) {
        this.metadata().set(ServerMetadata.PHASE, phase.key());
    }
}
