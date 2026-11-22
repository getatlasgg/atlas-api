package be.esmay.atlas.api.lock;

import java.util.Objects;

/**
 * How a lease ended.
 *
 * @param name the lease's name
 * @param token the token of the grant that ended
 * @param reason why it ended
 */
public record LeaseLost(String name, long token, LeaseLost.Reason reason) {

    /**
     * Checks that every part is present.
     *
     * @throws NullPointerException when the name or the reason is missing
     */
    public LeaseLost {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(reason, "reason");
    }

    /**
     * Why a lease ended.
     */
    public enum Reason {
        /** This server released it. */
        RELEASED,
        /** This server stopped ticking for longer than the lease timeout. */
        LAPSED,
        /** Another server holds the name now, with a higher token. */
        REPLACED,
        /** This server is shutting down. */
        SERVER_STOPPED
    }
}
