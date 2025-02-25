package com.seshasayee.jApp.service;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.springframework.security.core.userdetails.User;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider{

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(com.seshasayee.jApp.entity.User.builder().userName("rahul").password("rahul").build()),
                Arguments.of(com.seshasayee.jApp.entity.User.builder().userName("kajal").password("kajal").build())
        );
    }
}