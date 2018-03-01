package eta3;

import java.io.File; 
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

class HyvaTietaa2018Ohjelma {

	final private File xml = new File("hyvaTietaa2018.xml");
	final private File xsd = new File("hyvaTietaa2018.xsd");

	private Events events;

	public void toObject() throws JAXBException, SAXException {
		final JAXBContext jaxbContext = JAXBContext.newInstance(Events.class);
		final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		events = (Events) jaxbUnmarshaller.unmarshal(xml);
	}

	public void toXML() throws JAXBException, SAXException {

		final JAXBContext jaxbContext = JAXBContext.newInstance(Events.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
/*
		jaxbMarshaller.setProperty(
				Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,
				"hyvaTietaa2018.xsd");

		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(xsd);
		jaxbMarshaller.setSchema(schema);
*/
		jaxbMarshaller.marshal(events, xml);
	}

	// LAITA TÄSTÄ ALASPÄIN OLEVA KOODI Viopeen

	public void listaaEvents() {
		
	}
	
	public static void main(final String[] args) {

		HyvaTietaa2018Ohjelma sovellus = new HyvaTietaa2018Ohjelma();

		try {

			sovellus.toObject();

			Scanner input = new Scanner(System.in);
			int valinta = -1;

			do {
				System.out.println("\n1 = Listaa tapahtumat");
				System.out.println("2 = Hae tapahtumat nimellä");
				System.out.println("3 = Poista tapahtuma");
				System.out.println("4 = Muuta tapahtuman url");
				System.out.println("0 = Lopeta");
				System.out.print("Anna valintasi: ");
				valinta = input.nextInt();
				input.nextLine();
				switch (valinta) {
				case 1:
					sovellus.listaaEvents();
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				}

			} while (valinta != 0);

			sovellus.toXML();

		} catch (final JAXBException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			System.out.print("Validointi ei onnistunut");
		}
	}

}
