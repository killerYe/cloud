package chain.cloud.zuulserver.provider;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class UserServiceFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
//        return null;
        return "userservice";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
//        return null;
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
//                return null;
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
//                return null;
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
//                return null;
                String makeJson = "{\n" +
                        "        \"user\": {\n" +
                        "            \"id\": -3,\n" +
                        "            \"nickname\": \"fakeUser\",\n" +
                        "            \"avatar\": \"/users/avatar/user.png\"\n" +
                        "        },\n" +
                        "        \"serverPort\": 0000\n" +
                        "    }";
                return new ByteArrayInputStream(makeJson.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }
}
