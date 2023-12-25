package io.github.yvasyliev.deezer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import io.github.yvasyliev.deezer.http.DeezerHttpClient;
import io.github.yvasyliev.deezer.http.DefaultDeezerHttpClient;
import io.github.yvasyliev.deezer.methods.validators.AbstractResponseValidator;
import io.github.yvasyliev.deezer.methods.validators.ErrorValidator;
import io.github.yvasyliev.deezer.methods.validators.ResponseValidator;
import io.github.yvasyliev.deezer.methods.validators.WrongCodeValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@Builder
public class DeezerContext {
    private static final String DEEZER_API_HOST = "https://api.deezer.com";
    @NonNull
    private String deezerApiHost;
    @NonNull
    private DeezerHttpClient httpClient;
    @NonNull
    @JsonIgnore
    private ObjectMapper objectMapper;
    @NonNull
    private ResponseValidator responseValidator;

    public DeezerContext() {
        this(
                DEEZER_API_HOST,
                new DefaultDeezerHttpClient(),
                new ObjectMapper(),
                AbstractResponseValidator.createChain(new WrongCodeValidator(), new ErrorValidator())
        );
        this.objectMapper.setInjectableValues(new InjectableValues.Std().addValue(
                DeezerContext.class,
                this
        ));
        SerializationConfig serializationConfig = this.objectMapper.getSerializationConfig();
        this.objectMapper.setConfig(serializationConfig.with(serializationConfig.getAttributes().withSharedAttribute(
                DeezerContext.class,
                this
        )));
    }
}
