<?xml version="1.0" encoding="Shift_JIS"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
     xmlns:xalan="http://xml.apache.org/xslt">
<xsl:output method="xml" encoding="Shift_JIS" xalan:indent-amount="2"/>
<xsl:template match="/">


  <xsl:apply-templates/>

</xsl:template>

<xsl:template match="SOURCE">
  <tag>
  <xsl:apply-templates/>
  </tag>
</xsl:template>

<xsl:template match="FUNC">
    <br /><xsl:apply-templates/>
</xsl:template>

<xsl:template match="TYPE">
  <xsl:if test="boolean(name(..)='CAST')">
    <xsl:text>(</xsl:text>
  </xsl:if>
  <xsl:apply-templates />
  <xsl:if test="boolean(name(..)='CAST')">
    <xsl:text>)</xsl:text>
  </xsl:if>
  <xsl:text> </xsl:text>
</xsl:template>

<xsl:template match="FNAME">
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="ARGUMENTS">
  <xsl:text>(</xsl:text>
  <xsl:for-each select="*">
    <xsl:if test="position() != 1">
      <xsl:if test="not(boolean(name() = 'ARRAY'))">
        <xsl:text>,</xsl:text>
      </xsl:if>
    </xsl:if>
    <xsl:apply-templates select="."/>

  </xsl:for-each>
  <xsl:text>)</xsl:text>
</xsl:template>

<xsl:template match="BLOCK">
  <xsl:text>{</xsl:text><indent><br />
  <xsl:apply-templates select="DECLARATION"/><br/>
  <xsl:apply-templates select="STATEMENT"/></indent><br />}<br />
</xsl:template>

<xsl:template match="DECLARATION">
  <xsl:apply-templates/>;<br />
</xsl:template>

<xsl:template match="DECLARATOR">
  <xsl:for-each select="./*">
    <xsl:choose>
      <xsl:when test="position() = 1"/>
      <xsl:when test="boolean(../FNAME)"/>
      <xsl:when test="name() = 'ARRAY'"/>
      <xsl:when test="*[position()-1]"/>
      <xsl:otherwise>
        <xsl:text>,</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates select="."/>
  </xsl:for-each>
</xsl:template>

<xsl:template match="INILIST">
  <xsl:text>{</xsl:text>
    <xsl:for-each select="*">
    <xsl:apply-templates select="."/>
    <xsl:if test="position() != last()">
      <xsl:text>,</xsl:text>
    </xsl:if>
  </xsl:for-each>
  <xsl:text>}</xsl:text>
</xsl:template>

<xsl:template match="ASSIGNMENT">
  <xsl:for-each select="*">
    <xsl:if test="position() != last()">
      <xsl:apply-templates select="."/>
    </xsl:if>
  </xsl:for-each>
  <xsl:text>=</xsl:text>
  <xsl:apply-templates select="*[last()]"/>
</xsl:template>

<xsl:template match="RASSIGNMENT">
  <!--<xsl:if test="boolean(../LASSIGNMENT/ARRAY)">
    <xsl:text>{</xsl:text>
  </xsl:if>-->
  <xsl:apply-templates/>
  <!--<xsl:for-each select="*">
    <xsl:apply-templates select="."/>
    <xsl:if test="position() != last()">
      <xsl:text>,</xsl:text>
    </xsl:if>
  </xsl:for-each>-->
  <!--<xsl:if test="boolean(../LASSIGNMENT/ARRAY)">
    <xsl:text>}</xsl:text>
  </xsl:if>-->
</xsl:template>

<xsl:template match="STRUCT">
  <xsl:text>struct </xsl:text>
  <xsl:apply-templates select="STRUCTNAME"/>
  <xsl:if test="boolean(MEMBER)">{<indent><br />
  <xsl:apply-templates select="MEMBER"/></indent>
  <xsl:text>}</xsl:text>
  </xsl:if>
</xsl:template>

<xsl:template match="MEMBER">
  <xsl:apply-templates />;<br />
</xsl:template>

<xsl:template match="EXPRESSION">
  <xsl:for-each select="./*">
    <xsl:apply-templates select="."/>
    <xsl:choose>
      <xsl:when test="position() = last()">
      </xsl:when>
      <xsl:when test="boolean(../FNAME)">
      </xsl:when>
      <xsl:otherwise>
        <xsl:text>,</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:for-each>
  <xsl:choose>
    <xsl:when test="boolean(name(..) = 'ARRAY')">
    </xsl:when>
    <xsl:otherwise>
      <xsl:choose>
        <xsl:when test="boolean(name(..) = 'PARENTHESIS')">
          <xsl:if test="position() != last()">
           <xsl:text>;</xsl:text>
          </xsl:if>
        </xsl:when>
        <xsl:otherwise>;<br />
        </xsl:otherwise>
      </xsl:choose>
    </xsl:otherwise>
  </xsl:choose>
</xsl:template>

<xsl:template match="PARENTHESIS">
  <parenthesis><xsl:text>(</xsl:text>
  <xsl:apply-templates/>
  <xsl:text>)</xsl:text></parenthesis>
</xsl:template>

<xsl:template match="FOR">
  <xsl:text>for</xsl:text><state>
  <xsl:apply-templates /></state>
  <!--<xsl:apply-templates select="PARENTHESIS"/>

  <xsl:apply-templates select="*[last()]"/>-->
</xsl:template>

<xsl:template match="IF">
  <xsl:text>if</xsl:text><state>
  <!--<xsl:apply-templates select="PARENTHESIS"/>

  <xsl:apply-templates select="THEN"/>
  <xsl:if test="boolean(ELSE)">
  <xsl:apply-templates select="ELSE"/>
  </xsl:if>-->
  <xsl:apply-templates /></state>
</xsl:template>

<xsl:template match="ELSE">
  <xsl:text>else </xsl:text>

  <state><xsl:apply-templates /></state>
</xsl:template>

<xsl:template match="DOWHILE">
  <xsl:text>do</xsl:text>

  <state><xsl:apply-templates select="*[position()=1]"/></state>
  <xsl:text>while</xsl:text>
  <xsl:apply-templates select="PARENTHESIS"/>;
</xsl:template>

<xsl:template match="WHILE">
  <xsl:text>while</xsl:text><state>
  <xsl:apply-templates/></state>
</xsl:template>

<xsl:template match="SWITCH">
  <xsl:text>switch</xsl:text>
  <indent><xsl:apply-templates /></indent>
</xsl:template>

<xsl:template match="CASE">
  <xsl:for-each select="*">
    <xsl:choose>
      <xsl:when test="name()='CONSTANTEX'">
        <xsl:text>
case </xsl:text>
        <xsl:apply-templates select="." />
        <xsl:text> : </xsl:text>
      </xsl:when>
      <xsl:otherwise>
        <indent><xsl:apply-templates select="." /></indent>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:for-each>
</xsl:template>

<xsl:template match="DEFAULT">
  <xsl:text>
default : </xsl:text>
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="ARRAY">
  <xsl:text>[</xsl:text>
  <xsl:apply-templates/>
  <xsl:text>]</xsl:text>
</xsl:template>

<xsl:template match="NONE">
  <xsl:choose>
    <xsl:when test="../../PARENTHESIS">
    <xsl:text>;</xsl:text>
    </xsl:when>
  </xsl:choose>
</xsl:template>

<xsl:template match="AMP">
  <!--<xsl:text disable-output-escaping="yes">&amp;</xsl:text>-->
  <AMP />
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="DIV">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>/</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="PLUSE">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>+=</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="PLUS">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:if test="name(*[position()=2])='ARRAY'">
    <xsl:apply-templates select="*[position()=2]"/>
  </xsl:if>
  <xsl:text>+</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="MINUSE">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>-=</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="MINUS">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>-</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="EQUAL">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>==</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="SHIFTLEFTE">
  <xsl:apply-templates select="*[position()=1]"/>
  <!--<xsl:text disable-output-escaping="yes">&lt;&lt;=</xsl:text>-->
  <SHIFTLEFTE />
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="SHIFTLEFT">
  <xsl:apply-templates select="*[position()=1]"/>
  <!--<xsl:text disable-output-escaping="yes">&lt;&lt;</xsl:text>-->
  <SHIFTLEFT />
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="FORE">
  <xsl:apply-templates select="*[position()=1]"/>
  <!--<xsl:text disable-output-escaping="yes">&lt;=</xsl:text>-->
  <FORE />
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="FEWER">
  <xsl:apply-templates select="*[position()=1]"/>
  <!--<xsl:text disable-output-escaping="yes">&lt;</xsl:text>-->
  <FEWER />
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="SHIFTRIGHTE">
  <xsl:apply-templates select="*[position()=1]"/>
  <!--<xsl:text disable-output-escaping="yes">&gt;&gt;=</xsl:text>-->
  <SHIFTRIGHTE />
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="SHIFTRIGHT">
  <xsl:apply-templates select="*[position()=1]"/>
  <!--<xsl:text disable-output-escaping="yes">&gt;&gt;</xsl:text>-->
  <SHIFTRIGHT />
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="BORE">
  <xsl:apply-templates select="*[position()=1]"/>
  <!--<xsl:text disable-output-escaping="yes">&gt;=</xsl:text>-->
  <BORE />
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="GREATER">
  <xsl:apply-templates select="*[position()=1]"/>
  <!--<xsl:text disable-output-escaping="yes">&gt;</xsl:text>-->
  <GREATER />
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="ANDE">
  <xsl:apply-templates select="*[position()=1]"/>
  <!--<xsl:text disable-output-escaping="yes">&amp;=</xsl:text>-->
  <ANDE />
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="AND">
  <xsl:apply-templates select="*[position()=1]"/>
  <!--<xsl:text disable-output-escaping="yes">&amp;&amp;</xsl:text>-->
  <AND />
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="SAND">
  <!--<xsl:text disable-output-escaping="yes">&amp;</xsl:text>-->
  <SAND />
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="MTE">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>^=</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="MT">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>^</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="PIPEE">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>|=</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="PIPE">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>||</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="SPIPE">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>|</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="QUESTION">
  <xsl:text>?</xsl:text>
</xsl:template>

<xsl:template match="CORON">
  <xsl:text>:</xsl:text>
</xsl:template>

<xsl:template match="TIRUDA">
  <xsl:text>~</xsl:text>
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="NOTE">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>!=</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="NOT">
  <xsl:text>!</xsl:text>
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="RESTE">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>%=</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="REST">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>%</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="DIVE">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>/=</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>

</xsl:template><xsl:template match="BREAK">
  <xsl:text>break ;</xsl:text>
</xsl:template>

<xsl:template match="TYPEDEF">
  <xsl:text>typedef </xsl:text>
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="POINTER">
  <xsl:text>*</xsl:text>
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="POSTFIXPLUS">
  <xsl:apply-templates/>
  <xsl:text>++</xsl:text>
</xsl:template>

<xsl:template match="POSTFIXMINUS">
  <xsl:apply-templates/>
  <xsl:text>--</xsl:text>
</xsl:template>

<xsl:template match="ARROW">
  <xsl:apply-templates select="*[position()=1]"/>
  <!--<xsl:text disable-output-escaping="yes">-&gt;</xsl:text>-->
  <ARROW />
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="MULTIE">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>*=</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="MULTI">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>*</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="DOT">
  <xsl:apply-templates select="*[position()=1]"/>
  <xsl:text>.</xsl:text>
  <xsl:apply-templates select="*[position()=2]"/>
</xsl:template>

<xsl:template match="return">
  <xsl:text>return </xsl:text>
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="hole">
  <xsl:choose>
    <xsl:when test="name(*[position()=1])='LVALUE'">
      <xsl:apply-templates select="LVALUE"/>
      <xsl:text> [ </xsl:text>
      <xsl:value-of select="./@id"/>
      <xsl:text> ] </xsl:text>
      <xsl:apply-templates select="RVALUE"/>
    </xsl:when>
    <xsl:when test="name(*[position()=1])='LASSIGNMENT'">
      <xsl:apply-templates select="LASSIGNMENT"/>
      <xsl:text> [ </xsl:text>
      <xsl:value-of select="./@id"/>
      <xsl:text> ] </xsl:text>
      <xsl:apply-templates select="RASSIGNMENT"/>
    </xsl:when>
    <xsl:when test="@original='POSTFIXPLUS'" >
      <xsl:apply-templates/>
      <xsl:text> [ </xsl:text>
      <xsl:value-of select="./@id"/>
      <xsl:text> ] </xsl:text>   
    </xsl:when>
    <xsl:when test="@original='POSTFIXMINUS'" >
      <xsl:apply-templates/>
      <xsl:text> [ </xsl:text>
      <xsl:value-of select="./@id"/>
      <xsl:text> ] </xsl:text>   
    </xsl:when>
    <xsl:when test="@original='EXPRESSION'" >
      <xsl:text> [ </xsl:text>
      <xsl:value-of select="./@id"/>
      <xsl:text> ] </xsl:text>
      <xsl:apply-templates/><br/>
    </xsl:when>
    <xsl:when test="@original='STATEMENT'" >
      <xsl:text> [ </xsl:text>
      <xsl:value-of select="./@id"/>
      <xsl:text> ] </xsl:text>
      <xsl:apply-templates/><br/>
    </xsl:when>
    <xsl:when test="@original='BLOCK'" >
      <xsl:text> [ </xsl:text>
      <xsl:value-of select="./@id"/>
      <xsl:text> ] </xsl:text>
      <xsl:apply-templates/><br/>
    </xsl:when>
    <xsl:when test="@original='complex'" >
      <xsl:text> [ </xsl:text>
      <xsl:value-of select="./@id"/>
      <xsl:text> ] </xsl:text>
      <xsl:apply-templates/><br/>
    </xsl:when>
    <xsl:when test="@original='IF'" >
      <xsl:text> [ </xsl:text>
      <xsl:value-of select="./@id"/>
      <xsl:text> ] </xsl:text>
      <xsl:apply-templates/><br/>
    </xsl:when>
    <xsl:when test="@original='FOR'" >
      <xsl:text> [ </xsl:text>
      <xsl:value-of select="./@id"/>
      <xsl:text> ] </xsl:text>
      <xsl:apply-templates/><br/>
    </xsl:when>
    <xsl:when test="@original='WHILE'" >
      <xsl:text> [ </xsl:text>
      <xsl:value-of select="./@id"/>
      <xsl:text> ] </xsl:text>
      <xsl:apply-templates/><br/>
    </xsl:when>
    <xsl:otherwise>
      <xsl:text> [ </xsl:text>
      <xsl:value-of select="./@id"/>
      <xsl:text> ] </xsl:text>
      <xsl:apply-templates/>
    </xsl:otherwise>
  </xsl:choose>
</xsl:template>

</xsl:stylesheet> 