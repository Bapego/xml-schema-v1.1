package schema;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.FileInputStream;
import java.io.IOException;


public class MainSchema {

    public static void main(String[] args) throws SAXException, IOException, XMLStreamException {

        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/XML/XMLSchema/v1.1");

        Schema schema = factory.newSchema();

        Validator validator = schema.newValidator();
        validator.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.out.println(exception.getLineNumber() + ":" + exception.getColumnNumber() + "\t" + exception.getMessage());
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.out.println(exception.getLineNumber() + ":" + exception.getColumnNumber() + "\t" + exception.getMessage());
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.println(exception.getLineNumber() + ":" + exception.getColumnNumber() + "\t" + exception.getMessage());
            }
        });

        System.out.println("**************************** VALID **************************************");
        validator.validate(new StreamSource("xml/valid.xml"));
        System.out.println();


        System.out.println("*************************** INVALID *************************************");
        validator.validate(new StreamSource("xml/invalid.xml"));
        System.out.println();
    }
}
