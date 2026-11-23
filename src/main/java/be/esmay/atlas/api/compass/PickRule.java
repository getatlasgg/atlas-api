package be.esmay.atlas.api.compass;

import be.esmay.atlas.api.server.AtlasServer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Picks one server of a group for a player. Atlas only offers servers that
 * have room, are not dev servers and are not in a round the group keeps
 * players out of.
 */
@FunctionalInterface
public interface PickRule {

    /**
     * Picks a server.
     *
     * @param player the player who moves
     * @param candidates the servers Atlas offers, sorted by name and never empty
     * @return one of the candidates, or empty to send the player nowhere
     */
    Optional<AtlasServer> pick(UUID player, List<AtlasServer> candidates);
}
