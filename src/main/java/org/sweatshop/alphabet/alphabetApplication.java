package org.sweatshop.alphabet;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class alphabetApplication extends Application<alphabetConfiguration> {

    public static void main(final String[] args) throws Exception {
        new alphabetApplication().run(args);
    }

    @Override
    public String getName() {
        return "alphabet";
    }

    @Override
    public void initialize(final Bootstrap<alphabetConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final alphabetConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
