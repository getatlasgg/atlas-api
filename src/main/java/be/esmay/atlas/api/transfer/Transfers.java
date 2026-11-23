package be.esmay.atlas.api.transfer;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Hands players between servers so that one server at a time owns each
 * player's data. Atlas orders the steps; the plugin's {@link PlayerHandler}
 * does the saving and loading against its own store.
 */
public interface Transfers {

    /**
     * The most bytes of context one transfer carries.
     */
    int LONGEST_CONTEXT = 4096;

    /**
     * Makes this server take part in transfers. From then on Atlas claims each
     * player who joins, saves and releases each player who quits, and sends
     * players here when a transfer picks this server. A server without a
     * handler never receives a transfer.
     *
     * @param handler how this server freezes, saves and loads a player
     * @throws IllegalStateException when a handler is already registered
     */
    void handler(PlayerHandler handler);

    /**
     * Moves a player this server owns to {@code target}.
     *
     * @param player the player to move
     * @param target the server or group that receives the player
     * @return completes with how the transfer ended
     */
    CompletableFuture<TransferResult> send(UUID player, TransferTarget target);

    /**
     * Moves a player this server owns to {@code target}, handing the
     * receiving server {@code context} in its {@link Claim.Granted}. Atlas
     * never reads it, and deletes it with the transfer.
     *
     * @param player the player to move
     * @param target the server or group that receives the player
     * @param context up to {@link #LONGEST_CONTEXT} bytes, copied
     * @return completes with how the transfer ended
     * @throws IllegalArgumentException when the context is too long
     */
    CompletableFuture<TransferResult> send(UUID player, TransferTarget target, byte[] context);

    /**
     * Tells Atlas that a write under this server's token changed nothing,
     * because the store already holds a newer token. Atlas freezes the player
     * and kicks them from this server.
     *
     * @param player the player whose write was refused
     */
    void refused(UUID player);

    /**
     * Whether this server owns the player right now. A quit handler that
     * changes the world or a store, such as dropping items or spawning a
     * combat-log stand-in, runs only while this is true: once a lease is lost,
     * a save is refused or a transfer commits, another server has the
     * player's state.
     *
     * @param player the player
     * @return true while this server holds a valid lease on the player
     */
    boolean owns(UUID player);
}
