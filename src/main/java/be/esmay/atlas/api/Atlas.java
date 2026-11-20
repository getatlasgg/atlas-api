package be.esmay.atlas.api;

import be.esmay.atlas.api.event.Events;
import be.esmay.atlas.api.server.LocalServer;
import be.esmay.atlas.api.server.ServerDirectory;
import be.esmay.atlas.api.server.Setup;
import java.util.Optional;

/**
 * Atlas as a plugin on this server sees it. The Atlas plugin provides one
 * instance: on Paper through the services manager, on Velocity and
 * BungeeCord through the Atlas plugin's own instance.
 */
public interface Atlas {

    /**
     * The running servers of the whole network.
     *
     * @return the live view of those servers
     */
    ServerDirectory servers();

    /**
     * This server.
     *
     * @return this server as Atlas knows it
     */
    LocalServer self();

    /**
     * Where to listen for changes to the servers in {@link #servers()}.
     *
     * @return the events of this Atlas instance
     */
    Events events();

    /**
     * The map setup this server runs, when it is a setup server.
     *
     * @return the setup, or empty on every other server
     */
    Optional<Setup> setup();
}
