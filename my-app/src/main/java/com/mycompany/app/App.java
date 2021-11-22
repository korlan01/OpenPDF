package com.mycompany.app;
//package org.apache.batik.dom.svg;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
//import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
//import com.lowagie.text.pdf.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfString;
//import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Rectangle;

//import org.w3c.dom.svg.SVGDocument;
//import org.w3c.dom.svg.SVGUtil;
//import org.w3c.dom.svg.Dimension;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.Color;
import java.awt.Dimension;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.print.PrintTranscoder;
//import org.apache.batik.dom.svg.SVGDOMImplementation;

//import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.w3c.dom.svg.SVGDocument;

//import org.w3c.dom.svg.SVGDOMImplementation;
//import org.w3c.dom.svg.SVGUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;

public class App 
{
	/**
	* Runs a test to make a new HelloWorld document with clear text.
	*
	**/
	public static void main( String[] args ) throws FileNotFoundException
	{
		System.out.println("Hello World");
		// step 1: creation of a document-object
		Color backgroundColor = Color.pink;
		Document document = new Document();
		try {
			Rectangle pageSize = new Rectangle(100 / 25.4f * 72, 100 / 25.4f * 72);
			if (backgroundColor != null) {
				pageSize.setBackgroundColor(backgroundColor);
			}
			document.setPageSize(pageSize);
			FileOutputStream fin = new FileOutputStream("Hello World");
			final PdfWriter instance = PdfWriter.getInstance(document, fin);

			document.open();
			PdfContentByte cb = instance.getDirectContent();
			Graphics2D g2d = cb.createGraphics(pageSize.getWidth(), pageSize.getHeight());
			instance.getInfo().put(PdfName.CREATOR, new PdfString(Document.getVersion()));

			document.add(new Paragraph("Hello World"));
			//Code from requestor that should trip the issue but causes errors
			/*
			document.setPageSize(pageSize);

			TranscoderInput ti = new TranscoderInput(svgDoc);
			PrintTranscoder transcoder = new PrintTranscoder();
			transcoder.transcode(ti, null);

			PageFormat page = new PageFormat();
			Paper paper = new Paper();
			paper.setSize(pageSize.getWidth(), pageSize.getHeight());
			paper.setImageableArea(0, 0, pageSize.getWidth(), pageSize.getHeight());
			page.setPaper(paper);
			transcoder.print(g2, page, 0);
			*/
			g2d.dispose();
		} catch (DocumentException | IOException de) {
			System.err.println(de.getMessage());
		}

		document.close();
	}
}
