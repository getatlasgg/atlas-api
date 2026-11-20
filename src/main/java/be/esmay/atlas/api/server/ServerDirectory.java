package be.esmay.atlas.api.server;

import java.util.List;
import java.util.Optional;

/**
 * The running servers of the whole network, as the Atlas panel lists them.
 * The view follows the panel on its own; listen to
 * {@link be.esmay.atlas.api.event.Events} to hear when it changes.
 */
public interface ServerDirectory {

    /**
     * Every server in the view.
     *
     * @return the servers, sorted by name
     */
    List<AtlasServer> all();

    /**
     * Finds a server by its id.
     *
     * @param id the server's id
     * @return the server, or empty when the view does not hold it
     */
    Optional<AtlasServer> byId(String id);

    /**
     * Finds a server by its name.
     *
     * @param name the server's name
     * @return the server, or empty when the view does not hold it
     */
    Optional<AtlasServer> named(String name);

    /**
     * The servers of one group.
     *
     * @param group the group's name
     * @return the group's servers, sorted by name
     */
    List<AtlasServer> inGroup(String group);
}
