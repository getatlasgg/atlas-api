package be.esmay.atlas.api.server;

import be.esmay.atlas.api.drain.DrainListener;
import be.esmay.atlas.api.drain.Draining;
import be.esmay.atlas.api.drain.PlayerHold;
import be.esmay.atlas.api.event.Subscription;
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
     * Whether Atlas is replacing this server right now.
     *
     * @return the replacement and its deadline, or empty while this server takes players
     */
    Optional<Draining> draining();

    /**
     * Listens for Atlas starting or stopping to replace this server. A
     * listener added while this server already drains hears that start at once.
     *
     * @param listener hears each start and stop
     * @return the subscription, which ends when it is closed
     */
    Subscription onDraining(DrainListener listener);

    /**
     * Keeps the players on this server while Atlas replaces it, such as
     * while a region server saves and hands its lease to the replacement.
     * A hold taken while this server is not draining also counts once it is.
     *
     * @return the hold, which lets the players go when released
     */
    PlayerHold holdPlayers();

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
