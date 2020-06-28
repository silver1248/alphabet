package org.sweatshop.alphabet;

import io.dropwizard.Configuration;
import lombok.EqualsAndHashCode;
import lombok.Value;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;

@Value
@EqualsAndHashCode(callSuper=false)
public class AlphabetConfiguration extends Configuration {
    @NotEmpty String template;
    @NotEmpty String defaultName;

    @JsonCreator
    public AlphabetConfiguration(
            @JsonProperty("template") String template, @JsonProperty("defaultName") String defaultName)
    {
        this.template = template;
        this.defaultName = defaultName;
    }
}
