package codeanalis

import codeanalis.finder.ClosureFinder
import codeanalis.finder.GStringFinder
import codeanalis.finder.OperatorOwerloadingFInder
import codeanalis.finder.OperatorUssagesFinder

import java.nio.file.Files
import java.nio.file.Paths

import static groovy.io.FileType.FILES

/**
 * Created by andrii_korkoshko on 19.10.16.
 */
class Analiser {

    def analisers = [new ClosureFinder(), new GStringFinder(), new OperatorOwerloadingFInder(),new OperatorUssagesFinder()]

    def analise(path) {
        println "No ${path}"
        new File(path).eachFileRecurse(FILES) {
            if (it.name.endsWith('.groovy')) {
                println it.absolutePath
                Files.lines(Paths.get(it.absolutePath)).forEach({ line -> analisers.each { analiser -> analiser.find(line) } })
            }
        }
    }
}
