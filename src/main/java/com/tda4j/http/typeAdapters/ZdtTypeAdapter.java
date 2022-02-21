package com.tda4j.http.typeAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZdtTypeAdapter extends TypeAdapter<ZonedDateTime> {

  @Override
  public void write(JsonWriter out, ZonedDateTime value) throws IOException {
    out.value(value.toInstant().toEpochMilli());
  }

  @Override
  public ZonedDateTime read(JsonReader in) throws IOException {
    String date = in.nextString();
    String modifiedDate = date.substring(0, date.length() - 2);
    return ZonedDateTime.parse(modifiedDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
  }
}
