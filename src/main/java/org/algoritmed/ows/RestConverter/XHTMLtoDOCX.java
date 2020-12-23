package org.algoritmed.ows.RestConverter;

import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XHTMLtoDOCX {
    protected static final Logger logger = LoggerFactory.getLogger(XHTMLtoDOCX.class);

    public WordprocessingMLPackage Render(String xhtml) throws Docx4JException {
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();

        XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);

        wordMLPackage.getMainDocumentPart().getContent().addAll(XHTMLImporter.convert(xhtml, null));

        // System.out.println(XmlUtils.marshaltoString(wordMLPackage.getMainDocumentPart().getJaxbElement(),
        // true, true));

        return wordMLPackage;
    }

}
