package be.esmay.atlas.api.drain;

import java.time.Instant;
import java.util.Objects;

/**
 * Atlas is replacing this server: a fresh server on the live template took
 * its place, and the players move over to it.
 *
 * @param replacementId the id of the server that takes this server's players
 * @param deadline when Atlas stops this server, with or without players
 */
public record Draining(String replacementId, Instant deadline) {

    public Draining {
        Objects.requireNonNull(replacementId, "replacementId");
        Objects.requireNonNull(deadline, "deadline");

        if (replacementId.isBlank()) {
            throw new IllegalArgumentException("a replacement has an id");
        }
    }
}
