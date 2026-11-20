package be.esmay.atlas.api.server;

import java.util.Arrays;
import java.util.Locale;

/**
 * Where a server is in its game loop. Atlas never stops a server that is
 * {@link #IN_ROUND}. A server's phase is its {@link ServerMetadata#PHASE}
 * metadata, written as {@link #key()}.
 */
public enum GamePhase {
    WAITING,
    IN_ROUND,
    ENDING;

    /**
     * The phase as its metadata value, such as {@code in_round}.
     *
     * @return the lowercase name
     */
    public String key() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    /**
     * Reads a phase from its metadata value.
     *
     * @param key a value written by {@link #key()}, or null
     * @return the phase, or {@link #WAITING} when the value names none
     */
    public static GamePhase of(String key) {
        return Arrays.stream(GamePhase.values()).filter(phase -> phase.key().equals(key)).findFirst().orElse(GamePhase.WAITING);
    }
}
