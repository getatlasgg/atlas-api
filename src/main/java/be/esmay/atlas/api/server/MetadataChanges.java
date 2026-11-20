package be.esmay.atlas.api.server;

/**
 * Changes to this server's metadata that {@link ServerMetadata#update} applies
 * together. They follow the rules of {@link ServerMetadata#set} and
 * {@link ServerMetadata#remove}, and run in the order they were added.
 */
public interface MetadataChanges {

    /**
     * Sets a value.
     *
     * @param key one to 64 lowercase letters, digits, dots, dashes or underscores, starting with a letter or digit
     * @param value up to 256 characters
     * @return these changes
     * @throws IllegalArgumentException when the key or the value breaks those rules
     */
    MetadataChanges set(String key, String value);

    /**
     * Sets a number.
     *
     * @param key one to 64 lowercase letters, digits, dots, dashes or underscores, starting with a letter or digit
     * @param value the number, written in decimal
     * @return these changes
     * @throws IllegalArgumentException when the key breaks those rules
     */
    default MetadataChanges set(String key, long value) {
        return this.set(key, Long.toString(value));
    }

    /**
     * Removes a value. Removing a key that is not set does nothing.
     *
     * @param key the key to remove
     * @return these changes
     */
    MetadataChanges remove(String key);

    /**
     * Sets where this server is in its game loop.
     *
     * @param phase the new phase
     * @return these changes
     */
    default MetadataChanges phase(GamePhase phase) {
        return this.set(ServerMetadata.PHASE, phase.key());
    }
}
