package com.epam.cdp.caclulator

import spock.lang.Specification

/**
 * Created by Andrii_Korkoshko on 10/18/2016.
 */
class GroovyCalculatorSpec extends Specification {


    def instance = Spy(GroovyCalculator)


    def "simple success test"() {
        given:
            //Setup
        when:
            def result = instance.calculate("0+0")
        then:
            result == 0d
    }

}
