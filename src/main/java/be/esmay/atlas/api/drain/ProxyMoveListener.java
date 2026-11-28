package be.esmay.atlas.api.drain;

import java.util.Set;
import java.util.UUID;

/**
 * Hears when players of this server are about to move to another proxy, and
 * when they are back. Every call runs on Atlas's own thread.
 */
public interface ProxyMoveListener {

    /**
     * The players are about to leave for another proxy and come back to this
     * server. Take a hold with {@code holdProxyMoves()} here to keep them
     * until, for example, the round ends. Called again with the larger move
     * when more players join it before they leave.
     *
     * @param move the proxy, the players and the deadline
     */
    void moving(ProxyMove move);

    /**
     * A player of the move is back on this server.
     *
     * @param move the move the player was part of
     * @param player the player who came back
     */
    default void returned(ProxyMove move, UUID player) {
    }

    /**
     * The move is over: every player came back, the time to come back ran
     * out, or the rollout kept the proxy after all.
     *
     * @param move the move that ended
     * @param missing the players who left and did not come back in time
     */
    default void ended(ProxyMove move, Set<UUID> missing) {
    }
}
