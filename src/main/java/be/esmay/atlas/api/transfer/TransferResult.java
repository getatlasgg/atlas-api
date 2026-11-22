package be.esmay.atlas.api.transfer;

import java.util.Objects;

/**
 * How a transfer ended.
 */
public sealed interface TransferResult permits TransferResult.Moved, TransferResult.Cancelled, TransferResult.NotMoved {

    /**
     * The player arrived on {@code serverId}, which owns them now.
     *
     * @param serverId the receiving server's id
     */
    record Moved(String serverId) implements TransferResult {

        /**
         * Checks that the id is present.
         *
         * @throws NullPointerException when the id is missing
         */
        public Moved {
            Objects.requireNonNull(serverId, "serverId");
        }
    }

    /**
     * The transfer stopped before another server took the player, so this
     * server still owns them.
     *
     * @param reason why it stopped
     */
    record Cancelled(Reason reason) implements TransferResult {

        /**
         * Checks that the reason is present.
         *
         * @throws NullPointerException when the reason is missing
         */
        public Cancelled {
            Objects.requireNonNull(reason, "reason");
        }
    }

    /**
     * {@code serverId} claimed the player, but the proxy never moved them
     * there. Atlas works out the owner from where the player is connected.
     *
     * @param serverId the server that claimed the player
     */
    record NotMoved(String serverId) implements TransferResult {

        /**
         * Checks that the id is present.
         *
         * @throws NullPointerException when the id is missing
         */
        public NotMoved {
            Objects.requireNonNull(serverId, "serverId");
        }
    }

    /**
     * Why a transfer was cancelled.
     */
    enum Reason {
        /** This server does not own the player. */
        NOT_THE_OWNER,
        /** The player is in another transfer. */
        ALREADY_MOVING,
        /** No server of the target takes players or has room. */
        NO_SERVER_WITH_ROOM,
        /** The panel could not be reached. */
        PANEL_UNREACHABLE,
        /** This server did not save the player in time. */
        TIMED_OUT
    }
}
