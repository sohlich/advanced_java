package cz.fai.blog.provider;

import cz.fai.blog.dto.PostDto;
import cz.fai.blog.util.JsonUtil;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Custom message body writer
 * <p>
 * Created by Radomir Sohlich on 10/31/16.
 */
@Provider
@Produces("application/json")
public class PostDtoWriter implements MessageBodyWriter<PostDto> {

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return aClass == PostDto.class;
    }

    @Override
    public long getSize(PostDto postDto, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public void writeTo(PostDto o, Class<?> type,
                        Type t,
                        Annotation[] as,
                        MediaType mt,
                        MultivaluedMap<String, Object> mm,
                        OutputStream out)
            throws IOException, WebApplicationException {
        JsonUtil.writeJson(out, o);
    }
}

