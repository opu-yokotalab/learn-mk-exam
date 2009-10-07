<?xml version="1.0" encoding="Shift_JIS"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="xml" indent="yes" encoding="Shift_JIS"/>
	
	<xsl:template match="/">

		<variables>
			<value>

			<xsl:for-each select="SOURCE/FUNC/BLOCK/DECLARATION">
				<!--  �ϐ��̏������𓯎��ɍs��Ȃ��ꍇ  -->
				<xsl:for-each select="DECLARATOR/VARIABLE">
					<variable>
						<name><xsl:apply-templates/></name>
						<mark>$1</mark>
					</variable>
				</xsl:for-each>

				<!--  �ϐ��̏������𓯎��ɍs���ꍇ  -->
				<xsl:for-each select="DECLARATOR/ASSIGNMENT/LASSIGNMENT/VARIABLE">
					<variable>
						<name><xsl:apply-templates/></name>
						<mark>$2</mark>
					</variable>					
				</xsl:for-each>

			</xsl:for-each>

			</value>
		</variables>

	</xsl:template>

</xsl:stylesheet>