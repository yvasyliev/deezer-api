package io.github.yvasyliev;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JacksonInjectDemoV2 {
    public static void main(String[] args) throws JsonProcessingException {
        DeezerContext context = new DeezerContext();
        TypeReference<Page<Track>> pageTypeReference = new TypeReference<Page<Track>>() {
        };

        Page<Track> page = new PagingMethod<>(context, "/artist/27/top", pageTypeReference).execute();
        System.out.println(page);

        page = page.getNext().execute();
        System.out.println(page);
    }

    public interface HttpClient {
        String get(String url, Map<String, String> queryParams);
    }

    public static class MockHttpClient implements HttpClient {
        private int index = 5;

        @Override
        public String get(String url, Map<String, String> queryParams) {
            String next = url + "?index=" + index;
            index += 5;
            return "{\"data\":[],\"next\":\"" + next + "\"}";
        }
    }

    public static class DeezerContext {
        private HttpClient httpClient;
        private ObjectMapper objectMapper;
        private String apiHost;

        public DeezerContext() {
            this(new MockHttpClient(), new ObjectMapper(), "https://api.deezer.com");
            this.objectMapper.setInjectableValues(new InjectableValues.Std().addValue(
                    DeezerContext.class,
                    this
            ));
        }

        public DeezerContext(HttpClient httpClient, ObjectMapper objectMapper, String apiHost) {
            this.httpClient = httpClient;
            this.objectMapper = objectMapper;
            this.apiHost = apiHost;
        }

        public HttpClient getHttpClient() {
            return httpClient;
        }

        public void setHttpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
        }

        public ObjectMapper getObjectMapper() {
            return objectMapper;
        }

        public void setObjectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        public String getApiHost() {
            return apiHost;
        }

        public void setApiHost(String apiHost) {
            this.apiHost = apiHost;
        }
    }

    public interface Pageable {
    }

    public static class Track implements Pageable {
    }

    public static class Page<T extends Pageable> {
        @JsonProperty("data")
        private List<T> data;
        @JsonProperty("next")
        @JsonDeserialize(using = PagingMethodDeserializer.class)
        private PagingMethod<T> next;

        public Page() {
        }

        public Page(List<T> data, PagingMethod<T> next) {
            this.data = data;
            this.next = next;
        }

        public List<T> getData() {
            return data;
        }

        public void setData(List<T> data) {
            this.data = data;
        }

        public PagingMethod<T> getNext() {
            return next;
        }

        public void setNext(PagingMethod<T> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Page{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    public static abstract class Method<T> {
        @JacksonInject
        @JsonIgnore
        private DeezerContext deezerContext;
        @JsonProperty("path")
        private String path;
        @JacksonInject
        private TypeReference<T> resultType;

        public Method(DeezerContext deezerContext, String path, TypeReference<T> resultType) {
            this.deezerContext = deezerContext;
            this.path = path;
            this.resultType = resultType;
        }

        public abstract T execute() throws JsonProcessingException;

        @Override
        public String toString() {
            return "Method{" +
                    "deezerContext=" + deezerContext +
                    ", path='" + path + '\'' +
                    ", resultType=" + resultType +
                    '}';
        }

        public DeezerContext getDeezerContext() {
            return deezerContext;
        }

        public String getPath() {
            return path;
        }

        public TypeReference<T> getResultType() {
            return resultType;
        }
    }

    public static class GetMethod<T> extends Method<T> {
        public GetMethod(DeezerContext deezerContext, String path, TypeReference<T> resultType) {
            super(deezerContext, path, resultType);
        }

        @Override
        public T execute() throws JsonProcessingException {
            DeezerContext context = getDeezerContext();
            String rawResponse = context.getHttpClient().get(context.getApiHost() + getPath(), getQueryParams());
            ObjectMapper mapper = context.getObjectMapper();
            JsonNode jsonResult = mapper.readTree(rawResponse);
            // validate jsonResult
            return mapper.treeToValue(jsonResult, getResultType());
        }

        protected Map<String, String> getQueryParams() {
            return new HashMap<>();
        }
    }

    public static class PagingMethod<T extends Pageable> extends GetMethod<Page<T>> {
        @JsonProperty("index")
        private Integer index;

        @JsonCreator
        public PagingMethod(@JacksonInject DeezerContext deezerContext, @JsonProperty("path") String path, @JacksonInject TypeReference<Page<T>> resultType) {
            super(deezerContext, path, resultType);
        }

        @Override
        protected Map<String, String> getQueryParams() {
            Map<String, String> queryParams = super.getQueryParams();
            if (index != null) {
                queryParams.put("index", index.toString());
            }
            return queryParams;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "PagingMethod{" +
                    "index=" + index +
                    "} " + super.toString();
        }
    }

    public static class PagingMethodDeserializer<T extends Pageable> extends StdDeserializer<PagingMethod<T>> implements ContextualDeserializer {
        private ObjectMapper objectMapper;

        public PagingMethodDeserializer() {
            this(null, null);
        }

        protected PagingMethodDeserializer(ObjectMapper objectMapper, JavaType valueType) {
            super(valueType);
            this.objectMapper = objectMapper;
        }

        @Override
        public PagingMethod<T> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            URL url = new URL(p.getText());

            Map<String, String> dto = new HashMap<>();
            dto.put("path", url.getPath());
            for (String queryParam : url.getQuery().split("&")) {
                String[] keyVal = queryParam.split("=");
                dto.put(keyVal[0], keyVal[1]);
            }

            return objectMapper.convertValue(dto, getValueType());
        }

        @Override
        public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
            DeezerContext context = (DeezerContext) ctxt.findInjectableValue(DeezerContext.class.getName(), null, null);

            ObjectMapper mapper = context.getObjectMapper();
            JavaType propertyType = property.getType();

            InjectableValues.Std injectableValues = (InjectableValues.Std) mapper.getInjectableValues();
            injectableValues.addValue(TypeReference.class, new TypeReference<T>() {
                @Override
                public Type getType() {
                    return propertyType.getSuperClass().containedType(0);
                }
            });

            return new PagingMethodDeserializer<>(mapper, propertyType);
        }
    }
}
