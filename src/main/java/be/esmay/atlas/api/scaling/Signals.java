package be.esmay.atlas.api.scaling;

import java.util.concurrent.CompletableFuture;

/**
 * Named numbers shared by the whole network, such as a queue length. Scaling
 * rules start and close servers by the last value each signal sent.
 */
public interface Signals {

    /**
     * Sends a new value for a signal.
     *
     * @param name 1 to 64 lowercase letters, digits or underscores,
     *     starting with a letter
     * @param value zero or more
     * @return completes once the panel stored the value; fails with
     *     {@link be.esmay.atlas.api.lock.PanelUnreachable} while the panel
     *     cannot be reached
     * @throws IllegalArgumentException when the name or the value is out of
     *     range
     */
    CompletableFuture<Void> send(String name, int value);
}
