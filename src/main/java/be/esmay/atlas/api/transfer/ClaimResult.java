package be.esmay.atlas.api.transfer;

/**
 * What the store holds after a claim.
 */
public sealed interface ClaimResult permits ClaimResult.Claimed, ClaimResult.Superseded {

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
}
