<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<!--  output kertoo, mihin muotoon muunto tehdään  -->
<xsl:output method="html" indent="yes"/>
<!--
 Malli kertoo, kuinka XML-dokumentista tehdään HTML-sivu
-->
<xsl:template match="/">
<!--  Malli sisältää kokonaisen HTML-sivun rakenteen  -->
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type"/>
<title>Henkilöstö</title>
</head>
<body>
<h2>Henkilöstö</h2>
<!--
 Kutsutaan mallia jokaiselle henkilosto-elementin alla olevalle henkilo-elementille 
-->
<xsl:apply-templates select="henkilosto/henkilo"/>
</body>
</html>
</xsl:template>
<!--
 Malli kertoo, kuinka yksittäinen henkilo-elementti muunnetaan HTML-sivulle 
-->
<xsl:template match="henkilo">
<p>
<b>
<!--
 haetaan henkilo-elementin sisällä oleva nimi-elementti ja sen alta sukunimi-elementti
käytetään value-of komentoa, koska sukunimi-elementtejä on vain yksi 
-->
<xsl:value-of select="nimi/sukunimi"/>
<!--
 Kutsutaan mallia jokaiselle nimi-elementin alla olevalle etunimi-elementille.
käytetään apply-templates komentoa (kutsutaan mallia), koska etunimi-elementtejä voi olla 1-3 kappaletta 
-->
<xsl:apply-templates select="nimi/etunimi"/>
<!--
 Attribuutin arvo haetaan aina value-of -komennolla. Attribuuti erotetaan elementistä laittamalla attribuutin eteen @-merkki 
-->
(
<xsl:value-of select="@tunnus"/>
)
</b>
<!--  &#160; laittaa sivulle välilyönnin  -->
<br/>
Nimike:
<xsl:value-of select="nimike"/>
<!--
 Testataan, onko henkilo-elementin alla tyohuone-elementtiä 
-->
<xsl:if test="tyohuone">
<br/>
Työhuone:
<xsl:value-of select="tyohuone"/>
</xsl:if>
<br/>
Email:
<xsl:value-of select="email"/>
<br/>
Puhelin:
<xsl:apply-templates select="puhelin"/>
</p>
</xsl:template>
<!--
 etunimi-elementille on tehty malli, koska etunimi-elementtejä voi olla usea (1-3).
Malli kertoo kuinka etunimi-elementti näytetään HTML-sivulla 
-->
<xsl:template match="etunimi">
<!--
 select=".", koska halutaan hakea se elementti, jolle malli on tehty 
-->
<xsl:value-of select="."/>
</xsl:template>
<xsl:template match="puhelin">
<xsl:value-of select="."/>
(
<xsl:value-of select="@type"/>
)
</xsl:template>
</xsl:stylesheet>