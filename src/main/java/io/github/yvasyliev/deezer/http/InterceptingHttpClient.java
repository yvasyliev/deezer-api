package io.github.yvasyliev.deezer.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
@AllArgsConstructor
public class InterceptingHttpClient implements HttpClient {
    private HttpClient origHttpClient;
    private Interceptor<HttpRequest> requestInterceptor;
    private Interceptor<HttpResponse> responseInterceptor;

    public InterceptingHttpClient() {
        this(
                new DefaultHttpClient(),
                request -> {
                    System.out.println(request);
                    return request;
                },
                response -> {
                    System.out.println(response);
                    return response;
                }
        );
    }

    @Override
    public HttpResponse send(HttpRequest request) throws IOException {
        return responseInterceptor.intercept(origHttpClient.send(requestInterceptor.intercept(request)));
    }
}
