public interface UserService {

    void register(RegisterRequest request);

    String login(AuthRequest request);
}
