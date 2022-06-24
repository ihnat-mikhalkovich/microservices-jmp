package com.microservicesjmp.songapp.resourceprocessor.util;

import com.microservicesjmp.songapp.resourceprocessor.entity.SongMetadata;
import lombok.RequiredArgsConstructor;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.springframework.stereotype.Service;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class Mp3MetadataExtractorImpl implements Mp3MetadataExtractor {

    private final ContentHandler handler;
    private final Parser parser;
    private final ParseContext parseCtx;

    @Override
    public SongMetadata extract(InputStream inputStream) {
        final Metadata metadata = parse(inputStream);
        return this.mapMetadata(metadata);
    }

    private Metadata parse(InputStream inputStream) {
        final Metadata metadata = new Metadata();
        try {
            parser.parse(inputStream, handler, metadata, parseCtx);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }
        return metadata;
    }

    private SongMetadata mapMetadata(Metadata metadata) {
        final String name = metadata.get("dc:title");
        final String artist = metadata.get("xmpDM:artist");
        final String album = metadata.get("xmpDM:album");
        final String length = metadata.get("xmpDM:duration");
        final int year = Integer.parseInt(metadata.get("xmpDM:releaseDate"));

        return SongMetadata.builder()
                .name(name)
                .artist(artist)
                .album(album)
                .length(length)
                .year(year)
                .build();
    }
}
