package be.esmay.atlas.api.transfer;

import be.esmay.atlas.api.server.AtlasServer;
import java.util.Objects;

/**
 * Where a transfer sends a player.
 */
public sealed interface TransferTarget permits TransferTarget.Server, TransferTarget.Group {

    /**
     * One server, by its id or its name. An id wins when a name matches
     * another server's id.
     *
     * @param serverId the server's id, as {@code AtlasServer.id()} gives it,
     *                 or its name, such as {@code lobby-4}
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

        /**
         * Names the server from the directory.
         *
         * @param server the server, as {@code servers()} lists it
         * @throws NullPointerException when the server is missing
         */
        public Server(AtlasServer server) {
            this(Objects.requireNonNull(server, "server").id());
        }
    }

    /**
     * A server of the group, picked with the group's rule by the proxy that
     * holds the player.
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
