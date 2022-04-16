package com.github.helloteam.common.security.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.helloteam.common.security.exception.CustomOauthException;

import java.io.IOException;
import java.util.Map;

/**
 * 自定义的OauthException Serializer，定制异常返回结果
 *
 * @author zdz
 * @date 2022/04/11 19:57
 */
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

    public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("code", e.getHttpErrorCode());
        jsonGenerator.writeStringField("msg",  e.getMessage());
        jsonGenerator.writeObjectField("data", e.getOAuth2ErrorCode());
        if (e.getAdditionalInformation() != null) {
            for (Map.Entry<String, String> entry : e.getAdditionalInformation().entrySet()) {
                jsonGenerator.writeStringField(entry.getKey(), entry.getValue());
            }
        }
        jsonGenerator.writeEndObject();
    }

}
