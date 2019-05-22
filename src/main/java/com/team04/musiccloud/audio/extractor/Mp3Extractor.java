package com.team04.musiccloud.audio.extractor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class Mp3Extractor extends AudioExtractor {

  private static final String XMP_DM = "xmpDM:";
  private static final String TITLE = "title";
  private static final String AUTHOR = "Author";
  private static final String RELEASE_DATE = XMP_DM + "releaseDate";
  private static final String ALBUM = XMP_DM + "album";
  private static final String DURATION = XMP_DM + "duration";

  @Override
  public String getAudioAlbum(byte[] bytes) throws ExtractorException {
    return getMetadata(bytes).get(ALBUM);
  }

  @Override
  public String getAudioAuthor(byte[] bytes) throws ExtractorException {
    return getMetadata(bytes).get(AUTHOR);
  }

  @Override
  public String getAudioTitle(byte[] bytes) throws ExtractorException {
    return getMetadata(bytes).get(TITLE);
  }

  @Override
  public String getAudioReleaseDate(byte[] bytes) throws ExtractorException {
    return getMetadata(bytes).get(RELEASE_DATE);
  }

  @Override
  protected String getAudioDurationMs(byte[] bytes) throws ExtractorException {
    return getMetadata(bytes).get(DURATION);
  }

  private Metadata getMetadata(byte[] bytes) throws ExtractorException {
    InputStream inputStream = new ByteArrayInputStream(bytes);
    BodyContentHandler contentHandler = new BodyContentHandler();
    Metadata metadata = new Metadata();
    ParseContext parseContext = new ParseContext();

    try {
      new Mp3Parser().parse(inputStream, contentHandler, metadata, parseContext);
    } catch (IOException | SAXException | TikaException e) {
      throw new ExtractorException(e);
    }

    return metadata;
  }
}
