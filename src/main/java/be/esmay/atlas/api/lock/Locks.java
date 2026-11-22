package be.esmay.atlas.api.lock;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Leases on names shared by the whole network. One server holds a name at a
 * time, and each grant of a name carries a higher fencing token than the one
 * before it.
 */
public interface Locks {

    /**
     * The longest {@code wait} that {@link #acquire(String, Duration)} takes.
     */
    Duration LONGEST_WAIT = Duration.ofMinutes(5);

    /**
     * Asks the panel for a lease on {@code name}, waiting up to {@code wait}
     * while another server holds it.
     *
     * @param name 1 to 64 lowercase letters, digits, dots, dashes or
     *     underscores, starting with a letter or digit
     * @param wait how long to wait for the current holder, from zero up to
     *     {@link #LONGEST_WAIT}
     * @return the lease once granted; fails with {@link LeaseHeld} when the
     *     wait runs out and with {@link PanelUnreachable} while the panel
     *     cannot be reached
     * @throws IllegalArgumentException when the name or the wait is out of
     *     range
     */
    CompletableFuture<Lease> acquire(String name, Duration wait);

    /**
     * The lease this server holds on {@code name}.
     *
     * @param name the lease's name
     * @return the lease, or empty when this server does not hold it
     */
    Optional<Lease> held(String name);
}
