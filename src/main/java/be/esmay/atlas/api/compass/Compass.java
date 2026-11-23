package be.esmay.atlas.api.compass;

import be.esmay.atlas.api.transfer.TransferTarget;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Where players go. The proxy that holds a player picks the server in a
 * group, with the rule the Atlas panel set for that group.
 */
public interface Compass {

    /**
     * What a rule name holds: one to 64 lowercase letters, digits, dots,
     * dashes or underscores, starting with a letter or digit.
     */
    Pattern RULE_NAME = Pattern.compile("^[a-z0-9][a-z0-9._-]{0,63}$");

    /**
     * Moves a player without a transfer. Nothing is saved or claimed: the
     * player only changes servers. Use {@code transfers().send} when a server
     * owns the player's data.
     *
     * @param player the player to move
     * @param target the server, or the group whose rule picks one
     */
    void send(UUID player, TransferTarget target);

    /**
     * Registers a custom rule. A group whose rule in the panel is
     * {@code name} uses it on this proxy. A proxy without the rule picks the
     * server with the fewest players instead. Registering a name again
     * replaces the rule.
     *
     * @param name the name the panel shows, matching {@link #RULE_NAME}
     * @param rule how the rule picks
     * @throws IllegalArgumentException when the name does not match {@link #RULE_NAME}
     */
    void rule(String name, PickRule rule);
}
