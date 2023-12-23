package io.github.yvasyliev.deezer.methods;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.yvasyliev.deezer.DeezerContext;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;


//@JsonSerialize(converter = MethodToURLConverter.class)

@Data
@AllArgsConstructor
public abstract class Method<T> {
//    @JacksonInject(useInput = OptBoolean.FALSE)
//    @JsonProperty(value = "io.github.yvasyliev.deezer.DeezerContext", access = JsonProperty.Access.WRITE_ONLY)
//    @JsonIgnore
    @JacksonInject(useInput = OptBoolean.FALSE)
    private DeezerContext context;

    @JsonProperty("path")
    private String path;

//    @JacksonInject(useInput = OptBoolean.FALSE)
//    @JsonIgnore
//    @JsonProperty(value = "io.github.yvasyliev.deezer.DeezerContext", access = JsonProperty.Access.WRITE_ONLY)
    @JacksonInject(useInput = OptBoolean.FALSE)
    private TypeReference<T> responseType;

    //    @ConstructorProperties({"io.github.yvasyliev.deezer.DeezerContext", "path", "responseType"})
//    public Method(@JacksonInject(useInput = OptBoolean.FALSE) @JsonProperty("io.github.yvasyliev.deezer.DeezerContext") @NonNull DeezerContext context, @NonNull String path, @NonNull TypeReference<T> responseType) {
//        this.context = context;
//        this.path = path;
//        this.responseType = responseType;
//    }

    public abstract T execute() throws IOException;
}
