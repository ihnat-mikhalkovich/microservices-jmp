package com.microservicesjmp.songapp.resourceprocessor.config;

import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;

@Configuration
public class TikaConfig {

    @Bean
    public ContentHandler contentHandler() {
        return new DefaultHandler();
    }

    @Bean
    public Parser mp3Parser() {
        return new Mp3Parser();
    }

    @Bean
    public ParseContext parseContext() {
        return new ParseContext();
    }
}
