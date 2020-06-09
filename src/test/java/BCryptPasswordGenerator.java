import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordGenerator {
    public static void main(String[] args) {
        String passwordEncoded = new BCryptPasswordEncoder().encode("123456");
        System.out.println(passwordEncoded);
    }
}
