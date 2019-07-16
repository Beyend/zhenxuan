package com.zcf.zhenxuan.util.json.serializer;

import java.io.IOException;

import com.zcf.zhenxuan.util.EmojiUtil;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


/**
 * Emoji 字符串序列化转义输出
 *
 */
public class EmojiSerializer extends JsonSerializer<String> {

	@Override
	public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		if (StringUtils.isEmpty(value)) {
			jgen.writeString("");
		} else {
			jgen.writeString(EmojiUtil.resolveToEmojiFromByte(value));
		}
	}
}
