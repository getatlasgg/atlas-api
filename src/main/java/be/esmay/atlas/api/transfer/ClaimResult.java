package be.esmay.atlas.api.transfer;

import java.time.Instant;
import java.util.Objects;

/**
 * What the store holds after a claim.
 */
public sealed interface ClaimResult permits ClaimResult.Claimed, ClaimResult.Superseded, ClaimResult.Held {

    /**
     * The store now holds {@code token} for the player.
     *
     * @param token the store's fence after the claim, at least 1
     */
    record Claimed(long token) implements ClaimResult {

        /**
         * Checks the token.
         *
         * @throws IllegalArgumentException when the token is below 1
         */
        public Claimed {
            if (token < 1) {
                throw new IllegalArgumentException("a token is at least 1");
            }
        }
    }

    /**
     * The store already held a higher fence, so the claim changed nothing.
     * Atlas raises its counter above {@code storeFence} and grants again.
     *
     * @param storeFence the fence the store holds, at least 1
     */
    record Superseded(long storeFence) implements ClaimResult {

        /**
         * Checks the fence.
         *
         * @throws IllegalArgumentException when the fence is below 1
         */
        public Superseded {
            if (storeFence < 1) {
                throw new IllegalArgumentException("a fence is at least 1");
            }
        }
    }

    /**
     * Another server still holds the player in the store, so an offline claim
     * changed nothing. Atlas tries again until that server lets go or the
     * panel grants the player.
     *
     * @param heldBy the id of the server that holds the player
     * @param heldSince when that server took the player
     */
    record Held(String heldBy, Instant heldSince) implements ClaimResult {

        /**
         * Checks that every part is present.
         *
         * @throws NullPointerException when the holder or the time is missing
         */
        public Held {
            Objects.requireNonNull(heldBy, "heldBy");
            Objects.requireNonNull(heldSince, "heldSince");
        }
    }
}
