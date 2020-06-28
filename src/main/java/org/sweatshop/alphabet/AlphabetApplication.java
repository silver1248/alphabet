package org.sweatshop.alphabet;

import org.sweatshop.alphabet.health.AlphabetHealthCheck;
import org.sweatshop.alphabet.resources.AlphabetResources;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.vavr.jackson.datatype.VavrModule;

public class AlphabetApplication extends Application<AlphabetConfiguration> {

    public static void main(final String[] args) throws Exception {
        new AlphabetApplication().run(args);
    }

    @Override
    public String getName() {
        return "alphabet";
    }

    @Override
    public void initialize(final Bootstrap<AlphabetConfiguration> bootstrap) {
        bootstrap.getObjectMapper().registerModule(new VavrModule());
    }

    @Override
    public void run(AlphabetConfiguration configuration,
                    Environment environment) {
        final AlphabetResources resource = new AlphabetResources(
            configuration.getTemplate(),
            configuration.getDefaultName()
        );
        final AlphabetHealthCheck healthCheck =
            new AlphabetHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}