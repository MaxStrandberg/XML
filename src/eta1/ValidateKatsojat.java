// MUUTA ALLA OLEVAA RIVIÄ TARVITTAESSA
package eta1;
 
import static org.w3c.dom.Node.ELEMENT_NODE;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException; 

public class ValidateKatsojat {
	public static void main(String[] args) throws SAXException, IOException {

		final String xmlFile = "katsojat.xml";

		try {
			
			String schemaFile ="<?xml version='1.0' encoding='UTF-8'?> <xs:schema xmlns:xs='http://www.w3.org/2001/XMLSchema'> 	<xs:element name='toplista' > 		  		<xs:complexType> 		<xs:sequence> 		 		<xs:element name='tiedot' maxOccurs='unbounded'> 		<xs:complexType> 		<xs:sequence> 		<xs:element name='vuosi' type='xs:int'/> 		<xs:element name='viikko' type='xs:string'/> 		<xs:element name='lahde' type='xs:string'/> 		<xs:element name='saate' type='xs:string'/> 		</xs:sequence> 		</xs:complexType> 		</xs:element> 		 		<xs:element name='ohjelmat'> 		<xs:complexType> 		<xs:sequence> 		 		<xs:element name='ohjelma' maxOccurs='unbounded'> 		<xs:complexType>	 		<xs:sequence> 		<xs:element name='nimi' type='xs:string'/> 		<xs:element name='katsojamaara' type='xs:int'/> 	 	 		<xs:element name='aika'> 		<xs:complexType> 		<xs:attribute name='paiva' type='xs:string'/> 		<xs:attribute name='kello' type='xs:string'/> 		</xs:complexType> 		</xs:element> 		 	 		</xs:sequence> 		<xs:attribute name='jnro' type='xs:int'/> 		<xs:attribute name='kanava' type='xs:string'/> 		</xs:complexType> 		</xs:element> 		</xs:sequence> 		 		</xs:complexType> 	 		</xs:element> 		 		</xs:sequence> 		<xs:attribute name='tyyppi' type='xs:string'/> 		</xs:complexType> 	 	</xs:element> </xs:schema>   ";
			/* String schemaFile = "";*/
			
			StringReader reader = new StringReader(schemaFile);

			SchemaFactory factory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");

			Schema schema = factory.newSchema(new StreamSource(reader));
			Validator validator = schema.newValidator();

			Source source = new StreamSource(xmlFile);

			validator.validate(source);
			System.out
					.println("Tekemäsi XML Schema vastaa " + xmlFile + "-dokumenttia.");
			DocumentBuilderFactory factory2 = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory2.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(schemaFile));
			Document doc = builder.parse(is);

			ValidateKatsojat ohjelma = new ValidateKatsojat();
			ohjelma.listNodes(doc.getDocumentElement());
			ohjelma.count();

		} catch (SAXException ex) {
			System.out
					.println("Tekemäsi XML Schema ei vastaa " + xmlFile + "-dokumenttia, koska");
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Tiedostonkäsittely ei onnistu, koska");
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Käsittely ei onnistu, koska");
			System.out.println(ex.getMessage());
		}

	}

	private int elementCount = 0;

	private void listNodes(Node node) {
		if (node.getNodeType() == ELEMENT_NODE) {
			if (node.getNodeName().equalsIgnoreCase("xs:element")) {
				elementCount++;
			}

			NodeList list = node.getChildNodes();
			if (list.getLength() > 0) {
				for (int i = 0; i < list.getLength(); i++) {
					listNodes(list.item(i));
				}
			}
		}

	}

	private void count() {
		if (elementCount > 6) {
			System.out.println("XML Schema on tehty kokonaan.");
		} else {
			System.out.println("XML Schema on tehty puutteellisesti, koska xs:element määrä on liian pieni eli " + elementCount + " kappaletta.");
		}
	}
}
