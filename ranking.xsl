<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html lang="es">
            <head>
                <meta charset="UTF-8"/>
                <title>Ranking - El Despertar del Héroe</title>
                <link rel="stylesheet" href="styles.css"/>
            </head>
            <body>
                <div class="contenedor">
                    <h1>El Despertar del Héroe</h1>
                    <p class="subtitulo">Clasificación de Mejores Puntuaciones</p>

                    <table>
                        <thead>
                            <tr>
                                <th>Puesto</th>
                                <th>Jugador</th>
                                <th>Puntuación</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Ruta XPath para recorrer cada jugador -->
                            <xsl:for-each select="/ranking/jugador">
                                <tr>
                                    <td>#<xsl:value-of select="position()"/></td>
                                    <td><xsl:value-of select="nombre"/></td>
                                    <td><strong><xsl:value-of select="puntuacion"/></strong> pts</td>
                                </tr>
                            </xsl:for-each>
                        </tbody>
                    </table>

                    <!-- Funciones XPath de resumen -->
                    <p class="resumen">
                        Partidas registradas: <strong><xsl:value-of select="count(/ranking/jugador)"/></strong> |
                        Puntos totales acumulados: <strong><xsl:value-of select="sum(/ranking/jugador/puntuacion)"/></strong>
                    </p>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>