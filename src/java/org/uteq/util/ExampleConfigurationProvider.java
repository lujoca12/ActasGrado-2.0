/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.util;

import javax.servlet.ServletContext;
import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

/**
 *
 * @author USUARIO
 */
@RewriteConfiguration
public class ExampleConfigurationProvider extends HttpConfigurationProvider {

    @Override
    public Configuration getConfiguration(final ServletContext context) {
        return ConfigurationBuilder.begin()
                .addRule()
                .when(Direction.isInbound().and(Path.matches("/upa/{page}/")))
                .perform(Forward.to("/{page}.xhtml"))
                .addRule()
                .when(Direction.isInbound().and(Path.matches("/upa/recursos/{page1}.pdf")))
                .perform(Forward.to("/recursos/{page1}.pdf"))
                .addRule()
                .when(Direction.isInbound().and(Path.matches("/upa/evidencias/{page1}.pdf")))
                .perform(Forward.to("/evidencias/{page1}.pdf"));

    }

    @Override
    public int priority() {
        return 10;
    }
}
