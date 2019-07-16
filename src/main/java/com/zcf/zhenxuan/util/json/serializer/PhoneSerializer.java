package com.zcf.zhenxuan.util.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.util.StringUtils;
import java.io.IOException;

/**
 * 电话号码转化为中间带*号的字符串(186****1676)
 *
 */
public class PhoneSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        if (StringUtils.isEmpty(value)) {
            jgen.writeString("");
        } else {
            if (value.length() == 11) {
                StringBuilder sb = new StringBuilder(value);
                sb = sb.replace(3, 7, "****");
                jgen.writeString(sb.toString());
            } else {
                jgen.writeString(value);
            }
        }
    }
}
