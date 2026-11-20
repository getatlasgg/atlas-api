package be.esmay.atlas.api.event;

import be.esmay.atlas.api.server.AtlasServer;
import java.util.Objects;

/**
 * A server left the view.
 *
 * @param server the server as it was
 */
public record ServerRemoved(AtlasServer server) implements ServerEvent {

    public ServerRemoved {
        Objects.requireNonNull(server, "server");
    }
}
