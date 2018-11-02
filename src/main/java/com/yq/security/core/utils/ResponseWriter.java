package com.yq.security.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: yq
 * @Date: 2018-10-24 15:17
 * @Description:
 */
public class ResponseWriter extends Writer {
    protected final Logger logger = LoggerFactory.getLogger(ResponseWriter.class);

    private final Writer writer;

    private ResponseWriter(Writer writer){
        this.writer = writer;
    }

    public static class ResponseWriterBuilder implements Builder<ResponseWriter> {

        private final HttpServletResponse httpServletResponse;

        private final Map<String,String> headers = new LinkedHashMap<>();

        private final static String DEFAULT_CHARACTER_ENCODING = "UTF-8";

        //状态码
        private int status;
        //字符编码
        private String characterEncoding = DEFAULT_CHARACTER_ENCODING;

        private ResponseWriterBuilder(final HttpServletResponse httpServletResponse){
            httpServletResponse.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
            httpServletResponse.setContentType("application/json;charset=" + DEFAULT_CHARACTER_ENCODING);
            this.httpServletResponse = httpServletResponse;
        }

        public ResponseWriterBuilder setStatus(final int status){
            httpServletResponse.setStatus(status);
            return this;
        }

        public ResponseWriterBuilder setHeader(final String key,final String value){
            httpServletResponse.setHeader(key,value);
            return this;
        }

        @Override
        public ResponseWriter build() {
            Writer writer = null;
            try {
                writer = httpServletResponse.getWriter();
                return new ResponseWriter(writer);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("ResponseWriter创建错误");
            }
        }
    }

    /**
     *
     * 自定义
     *
     * @param httpServletResponse
     * @return
     */
    public static ResponseWriterBuilder custom(final HttpServletResponse httpServletResponse){
        return new ResponseWriterBuilder(httpServletResponse);
    }

    public void writeJson(Object body) throws IOException{
        String message = getMessage(body);
        writer.write(message);
        close();
    }

    private String getMessage(Object body)throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(body);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        writer.write(cbuf,off,len);
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
