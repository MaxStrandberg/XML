// MUUTA ALLA OLEVAA RIVIA TARVITTAESSA
package eta1;
 
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema; 
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidateCv {
	public static void main(String[] args) throws SAXException, IOException {
		final String schemaFile = "cv.xsd";

		try {
			
	        String xmlFile = "<?xml version='1.0' encoding='UTF-8'?> <Cv xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' 	xsi:noNamespaceSchemaLocation='cv.xsd'> 	 <Identification> 	 <PersonName> 	 <FirstName>Max</FirstName> 	 <Surname>Strandberg</Surname> 	 </PersonName> 	  	  	 <ContactInfo> 	 <AddressLine>Apajakuja 7B</AddressLine> 	 <PostalCode>01600</PostalCode> 	 <Municipality>Vantaa</Municipality> 	 <Country>Finland</Country> 	 <Email>maxstrandberg@hotmale.com</Email> 	 </ContactInfo> 	  	<Demographics> 	<Birthdate year='1989' month='05' day='05'/> 	<Gender>MALE</Gender> 	<Nationality>Finland</Nationality> 	</Demographics>  	 </Identification> 	  	 <WorkExperienceList> 	 <WorkExperience> 	 <From year='2010' month='05' day='05'/> 	 <To year='2011' month='02' day='10'/> 	 <Position>Perusmies</Position> 	 <Activities>Coding</Activities> 	 <Employer>Databoys.ru</Employer> 	 </WorkExperience> 	 </WorkExperienceList> </Cv>   ";
	        
	        StringReader reader = new StringReader(xmlFile);
			SchemaFactory factory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");

			File schemaLocation = new File(schemaFile);
			Schema schema = factory.newSchema(schemaLocation);

			Validator validator = schema.newValidator();

			Source source = new StreamSource(reader);

			validator.validate(source);
			System.out.println("Tekemäsi XML-dokumentti noudattaa "
					+ schemaFile + " XML Schemaa.");

		} catch (SAXException ex) {
			System.out.println("Tekemäsi XML-dokumentti ei noudata "
					+ schemaFile + " XML Schemaa, koska");
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Tiedostonkäsittely ei onnistu, koska");
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Käsittely ei onnistu, koska");
			System.out.println(ex.getMessage());
		}

	}
}
