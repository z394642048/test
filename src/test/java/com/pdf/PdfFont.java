package com.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
 
public class PdfFont extends XMLWorkerFontProvider {
 
	@Override
	public Font getFont(String fontname, String encoding, boolean embedded, float size, int style, BaseColor color) {
		String font = fontname;
		if(font==null){//字体家族
			font = "宋体";
		}
		if(size<=0){//size<0 会使内容重叠在一体
			size=10.5f;
		}
		return super.getFont(fontname, encoding, embedded, size, style, color);
	}
	
}