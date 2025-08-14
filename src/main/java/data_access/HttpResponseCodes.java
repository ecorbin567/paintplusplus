package data_access;

/**
 * Constants for HTTP 2xx Success response codes. Magic number bs.
 */
public final class HttpResponseCodes {
    public static final int SUCCESS_OK = 200;
    public static final int SUCCESS_CREATED = 201;
    public static final int SUCCESS_ACCEPTED = 202;
    public static final int SUCCESS_NON_AUTHORITATIVE_INFORMATION = 203;
    public static final int SUCCESS_NO_CONTENT = 204;

    public static final int REDIRECTION_MC = 300;
    public static final int REDIRECTION_MOVED_PERMANENTLY = 301;
    public static final int REDIRECTION_FOUND = 302;
    public static final int REDIRECTION_SEE_OTHER = 303;
    public static final int REDIRECTION_NOT_MODIFIED = 304;

    public static final int NOT_FOUND = 404;
    public static final int FORBIDDEN = 403;

    // random ones
    public static final int EIGHT = 8;
    public static final String BACKSLASH = "/";

    public static final String APIKEY = "apikey";
    public static final String AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String BEARER = "Bearer ";
    public static final String CONTENT_TYPE_JSON = "application/json";

    private HttpResponseCodes() {
        // prevent instantiation
    }
}
