// MUUTA ALLA OLEVAA RIVIÄ TARVITTAESSA
package eta2;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
  
public class XSLTFinnkino {

	public static void main(String[] args)
			throws javax.xml.transform.TransformerException, IOException {

		try {
		

			String xslt = "<?xml version='1.0' encoding='UTF-8'?> <xsl:stylesheet version='1.0' 	xmlns:xsl='http://www.w3.org/1999/XSL/Transform'> 	<xsl:output method='html' indent='yes' /> 	<xsl:template match='/'> 		<html> 			<head> 				<title>Elokuvat</title> 			</head> 			 			<body> 			<h3>Elokuvat</h3> 			<xsl:apply-templates select='Events/Event'/> 			</body>  		</html>  	</xsl:template>  	 	<xsl:template match='Event'> 						<xsl:variable name='letter'>T</xsl:variable> 		<p> 		 		Nimi: 			<xsl:value-of select='Title'/> (<xsl:value-of select='OriginalTitle'/>) <br/> 		Kuvaus: 		<xsl:value-of select='ShortSynopsis'/> <br/> 		Vuosi: 			<xsl:value-of select='ProductionYear'/> <br/> 		 		Esitykseen: 	<xsl:value-of select='substring-before(dtLocalRelease, $letter)'/><br/> 						 		Ohjaajat: 		<xsl:for-each select='Directors/Director'>  						<xsl:value-of select='FirstName'/> 						&#160; 						<xsl:value-of select='LastName'/> 						<xsl:if test='position() != last()'>          				<xsl:text>, </xsl:text>     					</xsl:if> 						</xsl:for-each>  <br/> 		 		 		Näyttelijät: 	<xsl:for-each select='Cast/Actor'> 						<xsl:value-of select='FirstName'/> 						&#160; 						<xsl:value-of select='LastName'/> 						<xsl:if test='position() != last()'>          				<xsl:text>, </xsl:text>     					</xsl:if> 						</xsl:for-each><br/> 		</p> 	  	</xsl:template> 	 	  	  </xsl:stylesheet> ";
			
			
			StringReader readerXSLT = new StringReader(xslt);
			final File xmlFile = new File("finnkino2.xml");
			final File result = new File("finnkino2.html");

			javax.xml.transform.Source xmlSource = new javax.xml.transform.stream.StreamSource(
					xmlFile);
			javax.xml.transform.Source xsltSource = new javax.xml.transform.stream.StreamSource(
					readerXSLT);
			javax.xml.transform.Result htmlResult = new javax.xml.transform.stream.StreamResult(
					result);

			javax.xml.transform.TransformerFactory transFact = javax.xml.transform.TransformerFactory
					.newInstance();

			javax.xml.transform.Transformer trans = transFact
					.newTransformer(xsltSource);

			trans.transform(xmlSource, htmlResult);
			System.out
					.println("Muunnoksen tulos on tiedostossa finnkino2.html");

		} catch (javax.xml.transform.TransformerException ex) {
			System.out.println("Muunnos ei onnistu, koska ");
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Ongelmia, koska ");
			System.out.println(ex.getMessage());
		}
	}

}
