package be.esmay.atlas.api.transfer;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * What a server does with a player's data. Atlas calls {@link #freeze} and
 * {@link #unfreeze} on the server's main thread, and {@link #save},
 * {@link #release} and {@link #claim} off it. Before a transfer, when a
 * player quits and when the server stops, {@link #save} starts only after
 * {@link #freeze} has returned.
 */
public interface PlayerHandler {

    /**
     * Stops the player from changing anything this server would save. This is
     * the place to copy what {@link #save} writes, since it runs on the main
     * thread and the next save follows it.
     *
     * @param player the player
     */
    void freeze(UUID player);

    /**
     * Lets the player play again.
     *
     * @param player the player
     */
    void unfreeze(UUID player);

    /**
     * Writes the player's data under {@code token}. A store that refuses a
     * write whose token is lower than the one it holds keeps an old owner
     * from overwriting a newer one.
     *
     * @param player the player
     * @param token the fencing token this server owns the player with
     * @return completes once the data is written
     */
    CompletableFuture<Void> save(UUID player, long token);

    /**
     * Takes ownership of the player in the store and loads their data.
     *
     * @param player the player
     * @param claim the token Atlas granted, or {@link Claim.Offline} while the
     *     panel cannot be reached
     * @return completes with the token the store holds afterwards
     */
    CompletableFuture<ClaimResult> claim(UUID player, Claim claim);

    /**
     * Clears this server as the player's holder in the store, so that another
     * server can claim them while the panel cannot be reached. Atlas calls it
     * after the quit save and waits for it before it releases the lease. A
     * store without a holder keeps the default, which changes nothing.
     *
     * @param player the player
     * @param token the fencing token the quit save was written under
     * @return completes once the store no longer names this server
     */
    default CompletableFuture<Void> release(UUID player, long token) {
        return CompletableFuture.completedFuture(null);
    }
}
