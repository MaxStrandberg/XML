package tyontekija;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

import tyontekija.Tyontekijat.Tyontekija;

public class TyontekijaHallinta {

	final private File xml = new File("tyontekijat.xml");
	final private File xsd = new File("tyontekijat.xsd");

	private Tyontekijat tyontekijat;

	public void toObject() throws JAXBException, SAXException {
		final JAXBContext jaxbContext = JAXBContext
				.newInstance(Tyontekijat.class);
		final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
/*
		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(xsd);
		jaxbUnmarshaller.setSchema(schema);
*/
		tyontekijat = (Tyontekijat) jaxbUnmarshaller.unmarshal(xml);
	}

	public void toXML() throws JAXBException, SAXException {

		final JAXBContext jaxbContext = JAXBContext
				.newInstance(Tyontekijat.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		jaxbMarshaller
				.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,
						"tyontekijat.xsd");
/*
		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(xsd);
		jaxbMarshaller.setSchema(schema);
*/
		jaxbMarshaller.marshal(tyontekijat, xml);
		jaxbMarshaller.marshal(tyontekijat, System.out);

	}

	public void lisaaTyontekija() {

		Scanner input = new Scanner(System.in);

	}

	public int haeTyontekija(int id) {
		List<Tyontekijat.Tyontekija> tekijaLista = tyontekijat.getTyontekija();
		int i = 0;
		int index = -1;

		while (index == -1 && i < tekijaLista.size()) {
			if (tekijaLista.get(i).getId() == id) {
				index = i;
			} else {
				i++;
			}
		}

/*
 		Iterator<Tyontekijat.Tyontekija> itr = tekijaLista.iterator();

		while (index == -1 && itr.hasNext()) {
			if (itr.next().getId() == id) {
				index = i;
			}
		}
*/
		return index;
	}

	public void poistaTyontekija() {
		Scanner input = new Scanner(System.in);
		System.out.print("Anna poistettava työntekijän id: ");
		int id = input.nextInt();
		input.nextLine();

	}

	public void listaaTyontekijat() {
		List <Tyontekijat.Tyontekija> tyontekijaLista = tyontekijat.getTyontekija();
		
		for (int i = 0; i < tyontekijaLista.size(); i++) {
			Tyontekijat.Tyontekija tyontekija = tyontekijaLista.get(i);
			String sukunimi = tyontekija.getSukunimi();
			BigDecimal palkka = tyontekija.getPalkka();
			String asema = tyontekija.getAsema();
			Tyontekijat.Tyontekija.Osasto osasto = tyontekija.getOsasto();
			List <Tyontekijat.Tyontekija.Etunimi> etunimiLista = tyontekija.getEtunimi();
			
			System.out.print("Nimi:");
			for(int j = 0; j < etunimiLista.size(); j++ ) {
				String etunimi = etunimiLista.get(j).getValue();
				System.out.print(" " + etunimi );
			}
			
			
			
			System.out.print(" " + sukunimi);
			
			System.out.println();
			if (palkka == null) {
				System.out.print("Palkka: 0");
			}else {
				System.out.print("Palkka: " + palkka);
			}
			System.out.println();
			
			if (osasto != null) {
				String osastoNimi = osasto.getNimi();
				System.out.print("Osasto: " + osastoNimi);
			}else {
				System.out.print("Osasto: -");
			}
			System.out.println();
			
			System.out.print("Asema: " + asema);
			System.out.println();
		}
	}

	public void paivitaTyontekija() {
		Scanner input = new Scanner(System.in);
		System.out.println("Anna päivitettävän työntekijän id: ");
		int id = input.nextInt();
		input.nextLine();

	}

	public static void main(final String[] args) {

		TyontekijaHallinta sovellus = new TyontekijaHallinta();

		try {

			sovellus.toObject();

			Scanner input = new Scanner(System.in);
			int valinta = -1;

			do {
				System.out.println("\n1 = Listaa työntekijät");
				System.out.println("2 = Lisää työntekijä");
				System.out.println("3 = Poista työntekijä");
				System.out.println("4 = Päivitä työntekijän sukunimi");
				System.out.println("0 = Lopeta");
				System.out.print("Anna valintasi: ");
				valinta = input.nextInt();
				input.nextLine();
				switch (valinta) {
				case 1:
					sovellus.listaaTyontekijat();
					break;
				case 2:
					sovellus.lisaaTyontekija();
					break;
				case 3:
					sovellus.poistaTyontekija();
					break;
				case 4:
					sovellus.paivitaTyontekija();
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