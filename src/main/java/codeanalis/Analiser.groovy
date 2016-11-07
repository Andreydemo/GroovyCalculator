package codeanalis

import codeanalis.finder.ClosureFinder
import codeanalis.finder.Finder
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

    def analisers = [new ClosureFinder(), new GStringFinder(), new OperatorOwerloadingFInder(), new OperatorUssagesFinder()]

    def int analise(path) {
        println "No ${path}"
        def map = [] as HashMap<Finder, Boolean>

        analisers.each {
            map << [(it): true]
        }

        new File(path).eachFileRecurse(FILES) {
            if (it.name.endsWith('.groovy')) {
                println it.absolutePath
                Files.lines(Paths.get(it.absolutePath)).forEach({ line ->
                    analisers.each { analiser ->
                        def result = analiser.find(line)
                        map << [analiser: map[analiser] & result]
                    }
                })
            }
        }
        def mark = 0
        map.each { k, v ->
            if (v) {
                mark += k.getMark()
            }
        }
        println "MARK!!! = ${mark}"
        return mark
    }
}
