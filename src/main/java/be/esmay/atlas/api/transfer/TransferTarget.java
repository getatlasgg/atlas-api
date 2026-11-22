package be.esmay.atlas.api.transfer;

import java.util.Objects;

/**
 * Where a transfer sends a player.
 */
public sealed interface TransferTarget permits TransferTarget.Server, TransferTarget.Group {

    /**
     * One server.
     *
     * @param serverId the server's id, as {@code AtlasServer.id()} gives it
     */
    record Server(String serverId) implements TransferTarget {

        /**
         * Checks that the id is present.
         *
         * @throws NullPointerException when the id is missing
         */
        public Server {
            Objects.requireNonNull(serverId, "serverId");
        }
    }

    /**
     * The server of a group with the fewest players and room for one more.
     *
     * @param name the group's name
     */
    record Group(String name) implements TransferTarget {

        /**
         * Checks that the name is present.
         *
         * @throws NullPointerException when the name is missing
         */
        public Group {
            Objects.requireNonNull(name, "name");
        }
    }
}
