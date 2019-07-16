package com.zcf.zhenxuan.util.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Double格式化（保留5位小数点）
 *
 */
public class DoubleSerializer extends JsonSerializer<Double> {

    @Override
    public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        if (value == null || 0 == value) {
            jgen.writeNull();
        } else {
            String v = String.format("%.5f", value);
            jgen.writeNumber(v);
        }
    }
   
}
