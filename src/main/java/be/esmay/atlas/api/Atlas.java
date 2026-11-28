package be.esmay.atlas.api;

import be.esmay.atlas.api.compass.Compass;
import be.esmay.atlas.api.event.Events;
import be.esmay.atlas.api.lock.Locks;
import be.esmay.atlas.api.scaling.ScaledGroup;
import be.esmay.atlas.api.scaling.Signals;
import be.esmay.atlas.api.server.LocalServer;
import be.esmay.atlas.api.server.ServerDirectory;
import be.esmay.atlas.api.server.Setup;
import be.esmay.atlas.api.transfer.Transfers;
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

    /**
     * Network-wide leases with fencing tokens.
     *
     * @return the leases of this server
     */
    Locks locks();

    /**
     * Player transfers between servers, built on the leases of
     * {@link #locks()}.
     *
     * @return the transfers of this server
     */
    Transfers transfers();

    /**
     * Where players go when they join, leave a server, or are sent into a
     * group.
     *
     * @return the compass of this server
     */
    Compass compass();

    /**
     * A Scaled group whose servers this server's code decides.
     *
     * @param name the group's name, matched without case or spacing
     * @return the group, checked by the panel on each ask
     * @throws IllegalArgumentException when the name is blank or longer
     *     than 100 characters
     */
    ScaledGroup group(String name);

    /**
     * The network's signals, which scaling rules read.
     *
     * @return the signals of this network
     */
    Signals signals();
}
