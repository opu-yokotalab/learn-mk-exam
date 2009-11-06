<?xml version="1.0" encoding="Shift_JIS"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="xml" indent="yes" encoding="Shift_JIS"/>
	
	<xsl:template match="/">

		<FOR>
			<PARENTHESIS>
				<EXPRESSION>
					<ASSIGNMENT>
						<LVALUE>
			<xsl:for-each select="SOURCE/FUNC/BLOCK/DECLARATION">

				<!--  変数の初期化を同時に行う場合  -->
				<xsl:for-each select="DECLARATOR/ASSIGNMENT">
					<xsl:if test="./LASSIGNMENT/VARIABLE==i">
						<VARIABLE><xsl:value-of select="./LASSIGNMENT/VARIABLE"/></VARIABLE>
					</xsl:if>
				</xsl:for-each>
			</xsl:for-each>
					</LVALUE>
					<RVALUE>
			<xsl:for-each select="SOURCE/FUNC/BLOCK/DECLARATION">
				<xsl:for-each select="DECLARATOR/ASSIGNMENT">
					<xsl:if test="./LASSIGNMENT/VARIABLE==i">
						<CONSTANT><xsl:value-of select="./RASSIGNMENT/CONSTANT"/></CONSTANT>		
					</xsl:if>
				</xsl:for-each>
			</xsl:for-each>
			</RVALUE>
					</ASSIGNMENT>
				</EXPRESSION>
			</PARENTHESIS>
			
			
			
		</FOR>

	</xsl:template>

</xsl:stylesheet>