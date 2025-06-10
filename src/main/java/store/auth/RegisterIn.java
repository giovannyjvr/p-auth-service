package store.auth;

// esse “interface” é usado em AuthParser e AuthResource
public interface RegisterIn {
    String name();
    String email();
    String password();
}
