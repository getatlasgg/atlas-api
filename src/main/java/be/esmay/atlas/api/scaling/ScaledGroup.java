package be.esmay.atlas.api.scaling;

import java.util.concurrent.CompletableFuture;

/**
 * A Scaled group whose servers this server's code decides. The group's
 * scaling must be set to Your code in the panel.
 */
public interface ScaledGroup {

    /**
     * The most servers one ask names.
     */
    int MOST_SERVERS = 1000;

    /**
     * The longest reason in characters.
     */
    int LONGEST_REASON = 200;

    /**
     * The name this group was asked for.
     *
     * @return the group's name, matched without case or spacing
     */
    String name();

    /**
     * Asks Atlas to keep {@code servers} servers in this group. Atlas keeps
     * the count within the group's fewest and most servers, the plan and the
     * room on the nodes, and never touches servers a person started.
     *
     * @param servers from zero up to {@link #MOST_SERVERS}
     * @param reason what the activity feed shows beside the ask, up to
     *     {@link #LONGEST_REASON} characters
     * @return the count Atlas keeps once the panel took the ask; fails with
     *     {@link ScaleRefused} when the group cannot follow it and with
     *     {@link be.esmay.atlas.api.lock.PanelUnreachable} while the panel
     *     cannot be reached
     * @throws IllegalArgumentException when servers or the reason is out of
     *     range
     */
    CompletableFuture<Integer> scaleTo(int servers, String reason);
}
