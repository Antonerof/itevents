<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <title>Ближайшие интересные Вам ивенты!</title>
                <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
                <meta name="robots" content="noindex, nofollow"/>
            </head>
            <body>
                <table cellpadding="0" cellspacing="0" width="100%" border="0" bgcolor="#f2f2f2">
                    <tr>
                        <td valign="top" align="center">
                            <table cellspacing="0" cellpadding="0" width="560" border="0"
                                   style="border-width:0px;border-color:#cccccc;border-style:solid;" bgcolor="#ffffff">
                                <tr>
                                    <td align="center" width="560">
                                        <table border="0" cellspacing="0" width="560" cellpadding="0">
                                            <tr>
                                                <td align="center" width="560"><img height="100" src="" width="530" border="0"
                                                                                    alt="Our logo"/></td>
                                            </tr>
                                        </table>
                                        <table width="560" border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                                <td width="560" height="20"></td>
                                            </tr>
                                        </table>

                                        <xsl:for-each select="events/event">
                                            <table width="520" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td align="left" valign="top"
                                                        style="color:#000000;font-family:Arial, Helvetica, sans-serif;line-height:17px;font-size:11px;">

                                                        <p style="margin:0px 0px 10px 0px; margin-bottom: 10px;">
                                                            <h3 style="margin:0px;">
                                                                <xsl:value-of select="title"/>
                                                            </h3>
                                                            <span style="font-style: italic;"><xsl:value-of select="eventDate"/></span><br/>
                                                            <span><xsl:value-of select="address"/> - <xsl:value-of select="contact"/></span><br/>
                                                            <a>
                                                                <xsl:attribute name='href'><xsl:value-of select="regLink"/></xsl:attribute>Register to <xsl:value-of select="title"/>
                                                            </a>
                                                        </p>
                                                    </td>
                                                </tr>
                                            </table>
                                            <table width="520" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="520" height="10"></td>
                                                </tr>
                                            </table>
                                        </xsl:for-each>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>