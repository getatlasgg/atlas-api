package be.esmay.atlas.api.scaling;

import java.util.Objects;

/**
 * The panel took the ask but the group cannot follow it.
 */
public final class ScaleRefused extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final Reason reason;

    /**
     * Describes why the group did not take the ask.
     *
     * @param group the group's name
     * @param reason why the panel refused
     */
    public ScaleRefused(String group, Reason reason) {
        super("group " + group + " cannot be scaled by code: " + Objects.requireNonNull(reason, "reason").name().toLowerCase().replace('_', ' '));
        this.reason = reason;
    }

    /**
     * Why the panel refused.
     *
     * @return the reason
     */
    public Reason reason() {
        return this.reason;
    }

    /**
     * Why a group did not take an ask.
     */
    public enum Reason {

        /**
         * No group on the network has that name.
         */
        NO_GROUP,

        /**
         * The group is a fixed group, which never scales.
         */
        FIXED_GROUP,

        /**
         * The group's rules decide its servers, not code.
         */
        RULES_DECIDE,

        /**
         * The group does not take asks from plugins.
         */
        PLUGINS_OFF
    }
}
