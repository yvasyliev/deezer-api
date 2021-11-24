package api.deezer.objects;

/**
 * Permissions that can be requested to user during authentication process.
 *
 * @see <a href="https://developers.deezer.com/api/permissions">https://developers.deezer.com/api/permissions</a>
 */
public enum Permission {
    /**
     * Access users basic information:
     * <ul>
     *     <li>name;</li>
     *     <li>firstname;</li>
     *     <li>profile picture.</li>
     * </ul>
     */
    BASIC_ACCESS("basic_access"),

    /**
     * Get the user's email.
     */
    EMAIL("email"),

    /**
     * Access user data any time.
     */
    OFFLINE_ACCESS("offline_access"),

    /**
     * Manage users' library.
     * Allows:
     * <ul>
     *     <li>add/rename a playlist;</li>
     *     <li>add/order songs in the playlist.</li>
     * </ul>
     */
    MANAGE_LIBRARY("manage_library"),

    /**
     * Manage users' friends.
     * Allows: add/remove a following/follower.
     */
    MANAGE_COMMUNITY("manage_community"),

    /**
     * Allow the application to delete items in the user's library.
     */
    DELETE_LIBRARY("delete_library"),

    /**
     * Allow the application to access the user's listening history.
     */
    LISTENING_HISTORY("listening_history");

    private final String value;

    Permission(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
