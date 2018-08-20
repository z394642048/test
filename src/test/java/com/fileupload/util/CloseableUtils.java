package com.fileupload.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * @className: CloseableUtils
 * @description:
 * @author: wwb
 * @date: 2017-10-30 15:02:16
 * @version: ver 1.0
 */
public class CloseableUtils {

    /**关闭可关闭对象
     * @Description:
     * @author: wwb_89
     * @Date: 2017-07-26 15:16:10
     * @param: closeable
     * @return: void
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
