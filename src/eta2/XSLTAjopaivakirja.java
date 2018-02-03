// MUUTA ALLA OLEVAA RIVIÄ TARVITTAESSA
package eta2;

import java.io.File; 
import java.io.IOException;
import java.io.StringReader;
 
public class XSLTAjopaivakirja {

	public static void main(String[] args)
			throws javax.xml.transform.TransformerException, IOException {

		try {

	
			String xslt = "<?xml version='1.0' encoding='UTF-8'?> <xsl:stylesheet version='1.0' 	xmlns:xsl='http://www.w3.org/1999/XSL/Transform'> 	<xsl:output method='html' indent='yes' /> 	<xsl:template match='/'> 		<html> 			<head> 				<title>Ajopaivakirja</title> 			</head> 			 			<body> 			<table border='1' cellpadding='5' cellspacing='0'> 			<tr> 			<th>Lähtöaika</th> 			<th>Lähtöpaikka</th> 			<th>Tuloaika</th> 			<th>Tulopaikka</th> 			<th>Matka ja korvaus</th> 			</tr> 			 			 			<xsl:for-each select='ajopaivakirja/ajo'> 			<xsl:variable name='alukema'><xsl:value-of select='alku/lukema'/></xsl:variable> 			<xsl:variable name='llukema'><xsl:value-of select='loppu/lukema'/></xsl:variable> 			<xsl:variable name='korvaus'>0.41</xsl:variable> 			<xsl:variable name='lasku' select='( $llukema - $alukema ) * $korvaus' /> 			<xsl:variable name='kilometrit' select='$llukema - $alukema'/> 			<tr>	 			 	<td><xsl:apply-templates select='alku/aika'/></td> 			 	<td><xsl:value-of select='alku/paikka'/></td> 				<td><xsl:apply-templates select='loppu/aika'/></td> 				<td><xsl:value-of select='loppu/paikka'/></td> 				<td><xsl:value-of select='$kilometrit'/> kilometriä, <xsl:value-of select='$lasku'/> euroa</td> 			 			</tr> 			</xsl:for-each> 			 			</table> 			 			 		 			</body>  		</html>  	</xsl:template> 	 	<xsl:template match='alku/aika'> 	<xsl:value-of select='@paiva'/> 	&#160; 	<xsl:value-of select='@kello'/> 	</xsl:template> 	 	<xsl:template match='loppu/aika'> 	<xsl:value-of select='@paiva'/> 	&#160; 	<xsl:value-of select='@kello'/> 	 	 	</xsl:template> 	 	 	 </xsl:stylesheet> ";
			
			
			StringReader readerXSLT = new StringReader(xslt);
			final File xmlFile = new File("ajopaivakirja2.xml");
			final File result = new File("ajopaivakirja2.html");

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
					.println("Muunnoksen tulos on tiedostossa ajopaivakirja2.html");

		} catch (javax.xml.transform.TransformerException ex) {
			System.out.println("Muunnos ei onnistu, koska ");
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Ongelmia, koska ");
			System.out.println(ex.getMessage());
		}
	}

}
