package be.esmay.atlas.api.transfer;

/**
 * How a server takes ownership of a player in its store.
 */
public sealed interface Claim permits Claim.Granted, Claim.Offline {

    /**
     * The panel granted this server the player with {@code token}. The store
     * raises its fence to the token, unless it already holds a higher one.
     *
     * @param token the fencing token, at least 1
     * @param context what the sending server passed along, empty when none
     */
    record Granted(long token, byte[] context) implements Claim {

        /**
         * Checks the token and copies the context.
         *
         * @throws IllegalArgumentException when the token is below 1 or the
         *     context is too long
         */
        public Granted {
            if (token < 1) {
                throw new IllegalArgumentException("a token is at least 1");
            }

            context = context == null ? new byte[0] : context.clone();

            if (context.length > Transfers.LONGEST_CONTEXT) {
                throw new IllegalArgumentException("a transfer carries at most " + Transfers.LONGEST_CONTEXT + " bytes of context");
            }
        }

        /**
         * What the sending server passed along.
         *
         * @return a copy of the context
         */
        @Override
        public byte[] context() {
            return this.context.clone();
        }
    }

    /**
     * The panel cannot be reached. The store raises its own fence by one and
     * returns it, and Atlas tells the panel once it is back.
     */
    record Offline() implements Claim {
    }
}
