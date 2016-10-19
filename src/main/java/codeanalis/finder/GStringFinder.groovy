package codeanalis.finder

import codeanalis.AnaliseHelper
import codeanalis.SyntaxElementContainer

/**
 * Created by andrii_korkoshko on 19.10.16.
 */
class GStringFinder implements Finder{
    @Override
    def find(String line) {
        SyntaxElementContainer elements = new AnaliseHelper().analise(line)
        if (elements.isGStringClosedPresent()) {
            println "GSTRING found ${line}"
        }
    }

}
