package be.esmay.atlas.api.event;

import be.esmay.atlas.api.server.AtlasServer;
import java.util.Objects;

/**
 * A server in the view changed, such as its name or address.
 *
 * @param previous the server as it was
 * @param server the server as it is now
 */
public record ServerUpdated(AtlasServer previous, AtlasServer server) implements ServerEvent {

    public ServerUpdated {
        Objects.requireNonNull(previous, "previous");
        Objects.requireNonNull(server, "server");

        if (!previous.id().equals(server.id())) {
            throw new IllegalArgumentException("an update keeps the server's id");
        }
    }
}
