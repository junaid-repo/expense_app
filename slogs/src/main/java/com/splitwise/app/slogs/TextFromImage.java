package com.splitwise.app.slogs;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.ITessAPI.TessBaseAPI;

public class TextFromImage {

	public static void main(String[] args) {

		String imageLocation = "/home/junaid/Pictures/IMG20231219151512.jpg";
		File image = new File(imageLocation);
		Tesseract tesseract = new Tesseract();

		tesseract.setDatapath("/home/junaid/Workspaces/expense_app/slogs/src/main/resources/tessdata");

		String result = "";
		try {
			result = tesseract.doOCR(image);
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);

	}

}