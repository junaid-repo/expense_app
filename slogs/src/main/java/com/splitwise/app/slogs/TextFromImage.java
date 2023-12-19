package com.splitwise.app.slogs;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TextFromImage {
	public String getImgText(String imageLocation) {
		ITesseract iT = new Tesseract();
		String imgText = "";
		try {
			imgText = iT.doOCR(new File(imageLocation));
			return imgText;
		} catch (TesseractException e) {
			e.printStackTrace();
			// return “Error while reading image”;
		}
		return imgText;
	}

	public static void main(String[] args) {
		TextFromImage app = new TextFromImage();
		System.out.println(app.getImgText("/home/junaid/Pictures/Screenshot_20231219_122624.png"));
	}
}