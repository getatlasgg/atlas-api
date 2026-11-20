package be.esmay.atlas.api.server;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Named values a server publishes about itself, such as {@code map} or
 * {@code mode}. Every server in the network can read them through
 * {@link AtlasServer#metadata()}, and the Atlas panel shows them.
 */
public interface ServerMetadata {

    /**
     * The most keys one server holds.
     */
    int LIMIT = 64;

    /**
     * The key that holds the server's {@link GamePhase}. Only a phase's
     * {@link GamePhase#key()} may be set under it.
     */
    String PHASE = "phase";

    /**
     * The key Atlas sets to {@code true} on a dev server, which runs a
     * template draft. Proxies list a dev server but never pick it for a
     * joining player.
     */
    String DEV = "atlas.dev";

    /**
     * Sets a value.
     *
     * @param key one to 64 lowercase letters, digits, dots, dashes or underscores, starting with a letter or digit
     * @param value up to 256 characters
     * @throws IllegalArgumentException when the key or the value breaks those rules, or a new key would pass {@link #LIMIT}
     */
    void set(String key, String value);

    /**
     * Sets a number.
     *
     * @param key one to 64 lowercase letters, digits, dots, dashes or underscores, starting with a letter or digit
     * @param value the number, written in decimal
     * @throws IllegalArgumentException when the key breaks those rules, or a new key would pass {@link #LIMIT}
     */
    default void set(String key, long value) {
        this.set(key, Long.toString(value));
    }

    /**
     * Removes a value. Removing a key that is not set does nothing.
     *
     * @param key the key to remove
     */
    void remove(String key);

    /**
     * Applies several changes at once. The network sees all of them or none:
     * when one breaks a rule, or the result would pass {@link #LIMIT}, nothing
     * changes.
     *
     * @param changes adds the changes, such as {@code changes -> changes.set("map", "dunes").phase(GamePhase.IN_ROUND)}
     * @throws IllegalArgumentException when a change breaks a rule, or the result would pass {@link #LIMIT}
     */
    void update(Consumer<MetadataChanges> changes);

    /**
     * Every value this server holds.
     *
     * @return the values, sorted by key
     */
    Map<String, String> all();
}
