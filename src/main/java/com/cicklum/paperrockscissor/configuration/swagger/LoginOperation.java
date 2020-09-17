package com.cicklum.paperrockscissor.configuration.swagger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiListingBuilder;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.Operation;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiListingScanningContext;
import springfox.documentation.spring.web.scanners.ApiModelReader;

import com.cicklum.paperrockscissor.service.dto.UserDto;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Multimap;

@Component
public class LoginOperation extends ApiListingScanner
{
    @Autowired
    private TypeResolver typeResolver;

    @Autowired
    public LoginOperation(ApiDescriptionReader apiDescriptionReader, ApiModelReader apiModelReader, DocumentationPluginsManager pluginsManager)
    {
	super(apiDescriptionReader, apiModelReader, pluginsManager);
    }

    @Override
    public Multimap<String, ApiListing> scan(ApiListingScanningContext context)
    {
	final Multimap<String, ApiListing> def = super.scan(context);

	final List<ApiDescription> apis = new LinkedList<>();

	final List<Operation> operations = new ArrayList<>();
	operations.add(new OperationBuilder(new CachingOperationNameGenerator())
		.method(HttpMethod.POST)
		.uniqueId("login")
		.parameters(Collections.singletonList(new ParameterBuilder()
				.name("userDto")
				.description("The username")
				.parameterType("body")
				.type(typeResolver.resolve(UserDto.class))
				.modelRef(new ModelRef("UserDto"))
				.build()))
		.summary("Log in") //
		.notes("Used to login, please copy 'Authorization header' response value to use in all rounds endpoints")
		.build());
	apis.add(new ApiDescription("/login", "Authentication documentation", operations, false));

	def.put("authentication", new ApiListingBuilder(context.getDocumentationContext().getApiDescriptionOrdering())
		.apis(apis)
		.description("Custom authentication")
		.build());

	return def;
    }
}