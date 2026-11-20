package be.esmay.atlas.api.event;

import be.esmay.atlas.api.server.AtlasServer;

/**
 * A change to the servers in the {@link be.esmay.atlas.api.server.ServerDirectory}.
 */
public sealed interface ServerEvent permits ServerAdded, ServerUpdated, ServerRemoved {

    /**
     * The server the change is about. For a removal, the server as it was.
     *
     * @return the server
     */
    AtlasServer server();
}
