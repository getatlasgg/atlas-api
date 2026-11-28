package be.esmay.atlas.api.drain;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * A rollout is replacing the proxy some players of this server came through.
 * Atlas moves them to another proxy and back onto this server.
 *
 * @param proxyId the id of the proxy that is being replaced
 * @param players the players of this server that move with it
 * @param deadline when the proxy moves its players even while a hold is open
 */
public record ProxyMove(String proxyId, Set<UUID> players, Instant deadline) {

    public ProxyMove {
        Objects.requireNonNull(proxyId, "proxyId");
        Objects.requireNonNull(deadline, "deadline");
        players = Set.copyOf(players);

        if (proxyId.isBlank()) {
            throw new IllegalArgumentException("a proxy has an id");
        }
    }
}
