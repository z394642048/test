package com.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Html2PdfTest {


    public static void main(String[] args) throws Exception {
        StringBuffer html = new StringBuffer();
        // DOCTYPE 必需写否则类似于 这样的字符解析会出现错误
        html.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\"/>\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>大苏打</p>\n" +
                "<p></p>\n" +
                "<p></p>\n" +
                "<table>\n" +
                "    <tr>\n" +
                "        <td colSpan=\"1\" rowSpan=\"1\">撒大苏打</td>\n" +
                "        <td colSpan=\"1\" rowSpan=\"1\">111</td>\n" +
                "        <td colSpan=\"1\" rowSpan=\"1\">啊啊啊</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td colSpan=\"1\" rowSpan=\"1\">发顺丰的</td>\n" +
                "        <td colSpan=\"1\" rowSpan=\"1\">222</td>\n" +
                "        <td colSpan=\"1\" rowSpan=\"1\">vv</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td colSpan=\"1\" rowSpan=\"1\">阿三大苏打</td>\n" +
                "        <td colSpan=\"1\" rowSpan=\"1\">333</td>\n" +
                "        <td colSpan=\"1\" rowSpan=\"1\">达到</td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "<p></p>\n" +
                "</body>\n" +
                "</html>");
        htmlToPdf(html.toString(), "dd");
    }

    /**
     * @param content html代码内容，注意：html代码中，body必须要加上style="font-family: SimSun;"。eg：<body style="font-family: SimSun;">
     * @param css
     * @throws IOException
     * @throws DocumentException
     */
    private static void htmlToPdf(String content, String css) throws IOException, DocumentException {
        ByteArrayInputStream in = new ByteArrayInputStream(content.getBytes());
        //创建pdf文件
        Document document = new Document();
        ByteArrayInputStream is = null;
        if (!"".equals(css)) {
            is = new ByteArrayInputStream(css.getBytes());
        }
        //要写入的文件名
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/usr/pdf/xx1.pdf"));// 创建书写器(Writer)
        document.open();
        //手动添加到pdf中的信息
        Paragraph elements = new Paragraph("dsssssssssssssssssssssssssssssss");
        elements.setAlignment(Element.ALIGN_CENTER);
        document.add(elements);
        Paragraph xx = new Paragraph("hkdhfsdfsdfdsfkfjghdkfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjfjsdfsd");
        xx.setAlignment(Element.ALIGN_LEFT);
        xx.setIndentationLeft(12);
        document.add(xx);

        //直接读取html代码，并将其内容转换成pdf格式
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, in, is, new PdfFont());
        document.close();
    }
}
