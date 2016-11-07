package codeanalis.finder

import codeanalis.AnaliseHelper
import codeanalis.SyntaxElementContainer

/**
 * Created by andrii_korkoshko on 19.10.16.
 */
class GStringFinder implements Finder{
    @Override
    def boolean find(String line) {
        SyntaxElementContainer elements = new AnaliseHelper().analise(line)
        def result = elements.isGStringClosedPresent()
        if (result) {
            println "GSTRING found ${line}"
        }
        return result
    }

    @Override
    double getMark() {
        return 0.5
    }
}
