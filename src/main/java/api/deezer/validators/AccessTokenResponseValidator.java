package api.deezer.validators;

import api.deezer.http.HttpResponse;

import java.util.function.Predicate;

/**
 * Validates {@code access_token} response.
 */
public class AccessTokenResponseValidator implements Predicate<HttpResponse> {
    @Override
    public boolean test(HttpResponse response) {
        return response.getBody().contains("access_token");
    }
}
