package be.esmay.atlas.api.event;

import be.esmay.atlas.api.server.AtlasServer;
import java.util.Objects;

/**
 * A server joined the view.
 *
 * @param server the new server
 */
public record ServerAdded(AtlasServer server) implements ServerEvent {

    public ServerAdded {
        Objects.requireNonNull(server, "server");
    }
}
