package com.zcf.zhenxuan.util.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Double格式化（保留5位小数点）
 *
 */
public class BigdecimalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        DecimalFormat df = new DecimalFormat("0.00");
        String str = df.format(value);
       // BigDecimal returnValue = new BigDecimal(str);
        jgen.writeString(str);

    }
    
}
