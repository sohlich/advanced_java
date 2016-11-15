/*
 */
package cz.fai.blog.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author František Špaček
 */
public final class JsonUtil {

    private static final String APPLICATION_JSON = "application/json";

    /**
     * Utility class pattern.
     */
    private JsonUtil() {
    }

    public static <T> T readJson(HttpServletRequest req, Class<T> resultType) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(req.getInputStream())) {
            return new Gson().fromJson(reader, resultType);
        }
    }

    public static void writeJson(HttpServletResponse resp, Object obj) throws IOException {
        resp.setContentType(APPLICATION_JSON);
        try (PrintWriter writer = resp.getWriter()) {
            new Gson().toJson(obj, writer);
        }
    }

    public static void writeJson(OutputStream os, Object obj) throws
            IOException {
        try (PrintWriter writer = new PrintWriter(os)) {
            new Gson().toJson(obj, writer);
        }
    }
}
