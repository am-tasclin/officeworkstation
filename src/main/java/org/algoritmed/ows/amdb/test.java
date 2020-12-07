package org.algoritmed.ows.amdb;

import com.vladsch.flexmark.docx.converter.DocxRenderer;
import com.vladsch.flexmark.ext.definition.DefinitionExtension;
import com.vladsch.flexmark.ext.emoji.EmojiExtension;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughSubscriptExtension;
import com.vladsch.flexmark.ext.ins.InsExtension;
import com.vladsch.flexmark.ext.superscript.SuperscriptExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.SimTocExtension;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.ext.wikilink.WikiLinkExtension;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.apache.commons.io.IOUtils;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Arrays;

public class test {
    final private static DataHolder OPTIONS = new MutableDataSet()
            .set(Parser.EXTENSIONS,
                    Arrays.asList(DefinitionExtension.create(), EmojiExtension.create(), FootnoteExtension.create(),
                            StrikethroughSubscriptExtension.create(), InsExtension.create(),
                            SuperscriptExtension.create(), TablesExtension.create(), TocExtension.create(),
                            SimTocExtension.create(), WikiLinkExtension.create()))
            .set(DocxRenderer.SUPPRESS_HTML, true)
            // the following two are needed to allow doc relative and site relative address
            // resolution
            .set(DocxRenderer.DOC_RELATIVE_URL, "file:///Users/astac/source/pdf") // this will be used for URLs like
            // 'images/...' or './' or '../'
            .set(DocxRenderer.DOC_ROOT_URL, "file:///Users/astac/source/pdf"); // this will be used for URLs like:
                                                                               // '/...'

    static String getResourceFileContent(String resourcePath) {
        StringWriter writer = new StringWriter();
        getResourceFileContent(writer, resourcePath);
        return writer.toString();
    }

    private static void getResourceFileContent(StringWriter writer, String resourcePath) {
        InputStream inputStream = test.class.getResourceAsStream(resourcePath);
        try {
            IOUtils.copy(inputStream, writer, "UTF-8");
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testFnc() {
        String markdown = "Цикл Шухарта — Демінга" + "\n======================"

                + "\n\n**Цикл Шухарта-Демінга** (**Цикл PDCA**, часто використовується назва **Цикл Демінга**) — модель безперервного поліпшення процесів, цикл PDCA — плануй (**Plan**), роби (**Do**), перевіряй (**Check**), впливай (**Act**). При її застосуванні в різноманітних областях діяльності (наприклад, [управління якістю](https://uk.wikipedia.org/wiki/Управління_якістю)) дозволяє ефективно керувати цією діяльністю на системній основі."

                + "\n\nЗміст"

                + "\n\n1.  [Цикл управління](#_371365)" + "\n2.  [Застосування](#_371373)"
                + "\n3.  [Див. також](#_371378)"

                + "\n\nЦикл управління[](#_371365)" + "\n---------------------------"

                + "\n\nМетодологія PDCA є найпростішим алгоритмом дій керівника по управлінню процесом і досягнення його цілей. Цикл управління починається з планування."

                + "\n\n1.  Планування: встановлення цілей і процесів, необхідних для досягнення цілей, планування робіт по досягненню цілей процесу і задоволення споживача, планування виділення і розподілу необхідних ресурсів."
                + "\n2.  Виконання: виконання запланованих робіт."
                + "\n3.  Перевірка: збір інформації та контроль результату на основі ключових показників ефективності (KPI), що вийшло в ході виконання процесу, виявлення та аналіз відхилень, встановлення причин відхилень."
                + "\n4.  Вплив (управління, коректування): вжиття заходів щодо усунення причин відхилень від запланованого результату, зміни в плануванні та розподілі ресурсів."

                + "\n\nЗастосування[](#_371373)" + "\n------------------------"

                + "\n\nУ практичній діяльності цикл PDCA застосовується багаторазово з різною періодичністю. При виконанні основної діяльності цикл PDCA застосовується з періодичністю циклів звітності та планування. При виконання коригуючих дій тривалість PDCA може бути менше або більше тривалості циклів звітності та планування та встановлюється в залежності від характеру, обсягу, тривалості і змісту заходів щодо усунення причин відхилення."

                + "\n\nДив. також[](#_371378)" + "\n----------------------"

                + "\n\n1.  [wikipedia:Цикл\\_Шухарта\\_—\\_Демінга](https://uk.wikipedia.org/wiki/Цикл_Шухарта_—_Демінга)";
        System.out.println("markdown\n");
        System.out.println(markdown);

        Parser PARSER = Parser.builder(OPTIONS).build();
        DocxRenderer RENDERER = DocxRenderer.builder(OPTIONS).build();

        Node document = PARSER.parse(markdown);

        // to get XML
        // String xml = RENDERER.render(document);

        // or to control the package
        WordprocessingMLPackage template = DocxRenderer.getDefaultTemplate();
        RENDERER.render(document, template);

        File file = new File("D:/flexmark-java-issue-176.docx");
        try {
            template.save(file, Docx4J.FLAG_SAVE_ZIP_FILE);
        } catch (Docx4JException e) {
            e.printStackTrace();
        }
    }

}
