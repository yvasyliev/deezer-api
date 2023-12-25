package io.github.yvasyliev.deezer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import io.github.yvasyliev.deezer.http.DefaultHttpClient;
import io.github.yvasyliev.deezer.http.HttpClient;
import io.github.yvasyliev.deezer.methods.validators.AbstractResponseValidator;
import io.github.yvasyliev.deezer.methods.validators.ErrorValidator;
import io.github.yvasyliev.deezer.methods.validators.ResponseValidator;
import io.github.yvasyliev.deezer.methods.validators.WrongCodeValidator;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeezerContext {
    private static final String DEEZER_API_HOST = "https://api.deezer.com";
    private String deezerApiHost;
    private HttpClient httpClient;
    @JsonIgnore
    private ObjectMapper objectMapper;
    private ResponseValidator responseValidator;

    public DeezerContext() {
        this(
                DEEZER_API_HOST,
                new DefaultHttpClient(),
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
