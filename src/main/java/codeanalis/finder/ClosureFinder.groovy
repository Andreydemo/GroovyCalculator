package codeanalis.finder

import codeanalis.AnaliseHelper
import codeanalis.SyntaxElementContainer

/**
 * Created by andrii_korkoshko on 19.10.16.
 */
class ClosureFinder implements Finder {

    @Override
    def find(String line) {
        if (hasClosure(line))
            println "Closure-> ${line}"
        return line
    }

    private boolean hasClosure(String line) {
        SyntaxElementContainer elements = new AnaliseHelper().analise(line)
        if (elements.isClosureClosedPresent()) {
            println "CLOSURE found ${line}"
        }

    }

}
