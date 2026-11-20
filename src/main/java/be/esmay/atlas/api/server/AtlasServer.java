package be.esmay.atlas.api.server;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Objects;

/**
 * A server Atlas runs in the network.
 *
 * @param id the server's id, which stays the same for the server's whole life
 * @param name the server's name, made of lowercase letters, digits and dashes
 * @param group the name of the group the server belongs to
 * @param address the server's private address and port
 * @param metadata the values the server publishes about itself
 * @param players how many players the server holds
 */
public record AtlasServer(String id, String name, String group, InetSocketAddress address, Map<String, String> metadata, Players players) {

    private static final int LONGEST_ID = 64;
    private static final int LONGEST_NAME = 80;
    private static final int LONGEST_GROUP = 64;

    public AtlasServer {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(group, "group");
        Objects.requireNonNull(address, "address");
        Objects.requireNonNull(players, "players");
        metadata = Map.copyOf(metadata);

        if (id.isEmpty() || id.length() > AtlasServer.LONGEST_ID || !id.chars().allMatch(AtlasServer::identifier)) {
            throw new IllegalArgumentException("a server id holds up to " + AtlasServer.LONGEST_ID + " letters, digits, dashes and underscores");
        }

        if (name.isEmpty() || name.length() > AtlasServer.LONGEST_NAME || !name.chars().allMatch(AtlasServer::lowercase)) {
            throw new IllegalArgumentException("a server name holds up to " + AtlasServer.LONGEST_NAME + " lowercase letters, digits and dashes");
        }

        if (group.isBlank() || group.length() > AtlasServer.LONGEST_GROUP) {
            throw new IllegalArgumentException("a group name holds 1 to " + AtlasServer.LONGEST_GROUP + " characters");
        }

        if (address.isUnresolved()) {
            throw new IllegalArgumentException("a server address is an IP address");
        }
    }

    /**
     * A server whose players are not known, as {@link Players#NONE}.
     *
     * @param id the server's id, which stays the same for the server's whole life
     * @param name the server's name, made of lowercase letters, digits and dashes
     * @param group the name of the group the server belongs to
     * @param address the server's private address and port
     * @param metadata the values the server publishes about itself
     */
    public AtlasServer(String id, String name, String group, InetSocketAddress address, Map<String, String> metadata) {
        this(id, name, group, address, metadata, Players.NONE);
    }

    /**
     * Where the server is in its game loop, read from its metadata.
     *
     * @return the phase, {@link GamePhase#WAITING} when the server set none
     */
    public GamePhase phase() {
        return GamePhase.of(this.metadata.get(ServerMetadata.PHASE));
    }

    private static boolean lowercase(int character) {
        return character == '-' || character >= '0' && character <= '9' || character >= 'a' && character <= 'z';
    }

    private static boolean identifier(int character) {
        return character == '_' || Character.isLetter(character) && character < 128 || AtlasServer.lowercase(character);
    }
}
