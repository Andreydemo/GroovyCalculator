package codeanalis

/**
 * Created by andrii_korkoshko on 19.10.16.
 */
class AnaliseHelper {
    static  analise(String line) {
        def elements = new SyntaxElementContainer()
        line.eachWithIndex { it, i ->
            if (it == "\$" && line[i + 1] == "{") {
                elements.openElement(i, "\${", "}")
            } else if (it == "{") {
                elements.openElement(i, "{", "}")
            }

            if (it == "}") {
                elements.closeElement(i, "}")
            }
        }
        elements
    }
}
