package com.mycompany.app;
//package org.apache.batik.dom.svg;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
//import com.lowagie.text.pdf.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfString;
//import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Rectangle;

import org.w3c.dom.svg.SVGDocument;
//import org.apache.batik.util.XMLResourceDescriptor;
//import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
//import org.w3c.dom.svg.SVGUtil;
//import org.w3c.dom.svg.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.imageio.stream.ImageInputStream;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.print.PrintTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.transcoder.TranscodingHints.Key;
import java.lang.Object;

//import org.apache.batik.dom.svg.SVGDOMImplementation;

//import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.w3c.dom.svg.SVGDocument;
//import org.geolatte.maprenderer.util.SVGDocumentIO; 

//import org.w3c.dom.svg.SVGDOMImplementation;
//import org.w3c.dom.svg.SVGUtil;

import java.io.FileNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.file.Paths;

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
		Color backgroundColor = null;// Color.pink;
		Document document = new Document();
		try {
			//Rectangle pageSize2 = new Rectangle(100 / 25.4f * 72, 100 / 25.4f * 72);
			Rectangle pageSize = new Rectangle(150 / 25.4f * 72, 100 / 25.4f * 72);
			//if (backgroundColor != null) {
		//		//pageSize.setBackgroundColor(backgroundColor);
		//		pageSize.setBackgroundColor(backgroundColor);
		//	}
			document.setPageSize(pageSize);
			//document.setPageSize(pageSize2);
			FileOutputStream fin = new FileOutputStream("pdf_rendered_from_svg");
			final PdfWriter instance = PdfWriter.getInstance(document, fin);

			document.open();
			//Image png = Image.getInstance("/home/group36/Desktop/crispy_text_test.png");
			//document.add(png);
			PdfContentByte cb = instance.getDirectContent();
			Graphics2D g2d = cb.createGraphics(pageSize.getWidth(), pageSize.getHeight());
			/*
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
									RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                    				RenderingHints.VALUE_STROKE_PURE);
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    				RenderingHints.VALUE_INTERPOLATION_BICUBIC);*/
			//DOMImplementation domImplementation = SVGDOMImplementation.getDOMImplementation();
			//String svgNS = "http://www.w3.org/2000/svg";
			//String parser = XMLResourceDescriptor.getXMLParserClassName();
        	//SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
            //SVGDocument svgDocument = (SVGDocument) factory.createDocument("/home/group36/Downloads/crispy_text_test.svg");// = (SVGDocument) domImplementation.createDocument(svgNS, "svg", null);
			//instance.getInfo().put(PdfName.CREATOR, new PdfString(Document.getVersion()));
			/*
			cb.saveState();
			cb.setColorFill(new Color(255,0,0,50));
			cb.setColorStroke(Color.blue);
			cb.rectangle(0,0,200,300);
			cb.fillStroke();
			cb.restoreState();

			//cb.saveState();
			//cb.setColorFill(new Color(255,0,0,50));
			cb.setColorStroke(Color.pink);
			cb.rectangle(20,450,100,100);
			cb.fillStroke();
			cb.restoreState();

			//add it so that text is on top of rectangle

			document.add(new Paragraph("Hello World"));
			//Code from requestor that should trip the issue but causes errors
*/
			renderPdf(document, g2d, pageSize);

			g2d.dispose();
		} catch (DocumentException | IOException de) {
			System.err.println(de.getMessage());
		}

		

		document.close();
	}

	public static void renderPdf(Document pdfDoc, Graphics2D g2, Rectangle pageSize) {
         pdfDoc.setPageSize(pageSize);
		try {
			//private static final Logger logger = Logger.getLogger(SVGImageReader.class);
			String svg_URI_input = Paths.get("/home/group36/Downloads/svg_feGaussianBlur_pdf_rendering_files/svg_file_with_feGaussianBlur.svg").toUri().toURL().toString();
			//ImageInputStream imageInputStream = (ImageInputStream) svg_URI_input;
			//BufferedImage ti = svgToBufferedImage(imageInputStream);
			//BufferedImageTranscoderOutput ti = new BufferedImageTranscoderOutput(svg_URI_input);
			//ByteArrayOutputStream resultByteStream = new ByteArrayOutputStream();
			TranscoderInput ti = new TranscoderInput(svg_URI_input);
			//TranscoderOutput to = new TranscoderOutput(resultByteStream);

			//PNGTranscoder pngTranscoder = new PNGTranscoder();
			PrintTranscoder transcoder = new PrintTranscoder();  
			transcoder.addTranscodingHint(PrintTranscoder.KEY_SCALE_TO_PAGE,false);
			transcoder.addTranscodingHint(PrintTranscoder.KEY_WIDTH, 600f);
			transcoder.addTranscodingHint(PrintTranscoder.KEY_HEIGHT, 400f);
			//pngTranscoder.transcode(ti,to);
			//TranscoderInput pti = new TranscoderInput(to);
			transcoder.transcode(ti, null);
			//transcoder.addTranscodingHint(PrintTranscoder.KEY_PIXEL_TO_MM,new Float(25.4f/1000));
			
			PageFormat page = new PageFormat();
			Paper paper = new Paper();
			paper.setSize(pageSize.getWidth(), pageSize.getHeight());
			paper.setImageableArea(0, 0, pageSize.getWidth(), pageSize.getHeight());
			page.setPaper(paper);
			transcoder.print(g2, page, 0);
		} catch (DocumentException | IOException de) {
			System.err.println(de.getMessage());
		}
  }
	/*
      private BufferedImage svgToBufferedImage(ImageInputStream inputStream)
        throws IOException {

        // The input to an ImageReader is an ImageInputStream but
        // the TranscoderInput constructor expects a standard ouput stream,
        // which is not compatible with ImageInputStream.  A work around to
        // this problem is to use a Wrapper class to encapsulate the
        // ImageInputStream in a class that extends InputStream.
        ImageInputStreamAdaptor imageInputStreamAdaptor =
            new ImageInputStreamAdaptor(inputStream);

        // create a servlet
        ImageTranscoder bufferedImgTranscoder =
            new BufferedImageTranscoder();

        TranscoderInput transcoderInput =
            new TranscoderInput(imageInputStreamAdaptor);

        // create the servlet output
        BufferedImageTranscoderOutput output =
            new BufferedImageTranscoderOutput();

        // perform the transcoding
        try {
            bufferedImgTranscoder.transcode(transcoderInput, output);
        } catch (TranscoderException e) {
            String imagePath = transcoderInput.getURI();
            logger.error("svg-transcoding-error" + imagePath, e);
            throw new IOException(e.getLocalizedMessage());
        }
        return output.getBufferedImage();
    }*/
}
