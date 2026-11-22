package be.esmay.atlas.api.lock;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * A name this server holds. The panel ends the lease when this server
 * releases it, stops, or stops ticking for longer than the network's lease
 * timeout. While the panel is unreachable the lease stays held.
 */
public interface Lease {

    /**
     * The name this lease holds.
     *
     * @return the name
     */
    String name();

    /**
     * The fencing token of this grant. A store that keeps the highest token
     * it has seen for the name and refuses writes with a lower one cannot be
     * written to by a holder whose lease has ended.
     *
     * @return the token, at least 1
     */
    long token();

    /**
     * Whether this server still holds the lease, as far as it has heard.
     *
     * @return false once the lease has ended
     */
    boolean valid();

    /**
     * Runs {@code listener} once when the lease ends, right away when it has
     * ended already.
     *
     * @param listener what to run, on the thread that heard the lease end
     */
    void onLost(Consumer<LeaseLost> listener);

    /**
     * Gives the name back. The lease stops being valid at once, and the
     * returned future completes once the panel has heard it.
     *
     * @return completes when the panel has released the lease
     */
    CompletableFuture<Void> release();
}
