package com.zcf.zhenxuan.util.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化
 *
 */
public class DateFormat2Serializer extends JsonSerializer<Date> {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");

    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        if (value == null) {
            jgen.writeNull();
        } else {
            jgen.writeString(sdf.format(value));
        }
    }
}
