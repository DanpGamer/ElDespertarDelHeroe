import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ProcessingInstruction;

public class ExportarRankingXML {

    public static void exportar(String[] nombres, int[] puntuaciones) {
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.newDocument();

            // 1. Etiqueta raíz <ranking>
            Element raiz = documento.createElement("ranking");
            documento.appendChild(raiz);

            // 2. Enlace al XSLT
            ProcessingInstruction xslt = documento.createProcessingInstruction(
                "xml-stylesheet", "type=\"text/xsl\" href=\"ranking.xsl\""
            );
            documento.insertBefore(xslt, raiz);

            // 3. Volcar arrays paralelos al árbol DOM
            for (int i = 0; i < nombres.length; i++) {
                if (nombres[i] != null) {
                    Element jugador = documento.createElement("jugador");
                    raiz.appendChild(jugador);

                    Element nombre = documento.createElement("nombre");
                    nombre.setTextContent(nombres[i]);
                    jugador.appendChild(nombre);

                    Element puntos = documento.createElement("puntuacion");
                    puntos.setTextContent(String.valueOf(puntuaciones[i]));
                    jugador.appendChild(puntos);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            DOMSource origen = new DOMSource(documento);

            // PASO A: Guardar/actualizar ranking.xml con su DTD
            Transformer transformerXML = transformerFactory.newTransformer();
            transformerXML.setOutputProperty(OutputKeys.INDENT, "yes");
            transformerXML.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformerXML.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "ranking.dtd");
            transformerXML.transform(origen, new StreamResult(new File("ranking.xml")));

            // PASO B: Transformar con ranking.xsl y actualizar el ranking.html existente
            StreamSource plantillaXSL = new StreamSource(new File("ranking.xsl"));
            Transformer transformerHTML = transformerFactory.newTransformer(plantillaXSL);
            transformerHTML.transform(origen, new StreamResult(new File("ranking.html")));

            System.out.println("ranking.xml y ranking.html actualizados correctamente.");

        } catch (Exception e) {
            System.out.println("Error al exportar el ranking: " + e.getMessage());
        }
    }
}