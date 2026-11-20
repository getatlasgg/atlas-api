package be.esmay.atlas.api.server;

/**
 * How many players a server holds.
 *
 * @param online the players on the server now, which may pass {@code slots} because operators bypass the cap
 * @param slots the server's player cap, or 0 when it sets none
 */
public record Players(int online, int slots) {

    /**
     * No players and no cap, which a server shows until its plugin first reports.
     */
    public static final Players NONE = new Players(0, 0);

    public Players {
        if (online < 0 || slots < 0) {
            throw new IllegalArgumentException("a player count is 0 or more");
        }
    }

    /**
     * Whether the server sets a player cap.
     *
     * @return true when {@link #slots()} is above 0
     */
    public boolean capped() {
        return this.slots > 0;
    }
}
