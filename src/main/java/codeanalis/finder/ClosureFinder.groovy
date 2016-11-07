package codeanalis.finder

import codeanalis.AnaliseHelper
import codeanalis.SyntaxElementContainer

/**
 * Created by andrii_korkoshko on 19.10.16.
 */
class ClosureFinder implements Finder {

    @Override
    def boolean find(String line) {
        def result = hasClosure(line)
        if (result)
            println "Closure-> ${line}"
        return result
    }

    @Override
    def double getMark() {
        return 1
    }

    private boolean hasClosure(String line) {
        SyntaxElementContainer elements = new AnaliseHelper().analise(line)
        if (elements.isClosureClosedPresent()) {
            println "CLOSURE found ${line}"
        }

    }

}
