package codeanalis

import codeanalis.entity.SyntaxElement

/**
 * Created by andrii_korkoshko on 19.10.16.
 */
class SyntaxElementContainer {

    Set<SyntaxElement> allElements = [] as LinkedHashSet
    Set<SyntaxElement> openedElements = [] as LinkedHashSet
    Set<SyntaxElement> closedElements = [] as LinkedHashSet

    def openElement(int index, String symbol, String closedSympol) {
        SyntaxElement syntaxElement = new SyntaxElement();
        syntaxElement.indexStart = index
        syntaxElement.symbol = symbol
        syntaxElement.closedSymbol = closedSympol

        allElements.add(syntaxElement)
        openedElements.add(syntaxElement)
    }

    def closeElement(int index, String closeSymbol) {
        for (SyntaxElement it : openedElements) {
            if (it.closedSymbol == closeSymbol) {
                closedElements.add(it)
                break;
            }
        }
        openedElements.removeAll(closedElements);

    }

    def boolean isClosureClosedPresent() {
        for (SyntaxElement it : closedElements) {
            if (it.symbol == "{") {
                return true;
            }
        }
        return false;
    }

    def boolean isGStringClosedPresent() {
        for (SyntaxElement it : closedElements) {
            if (it.symbol == "\${") {
                return true;
            }
        }
        return false;
    }
}
