package api.deezer.validators;

import java.util.function.Predicate;

/**
 * Validates {@code access_token} response.
 */
public class AccessTokenResponseValidator implements Predicate<String> {
    @Override
    public boolean test(String response) {
        return response.contains("access_token");
    }
}
