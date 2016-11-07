package com.epam.cdp.calculator

import spock.lang.Specification
import spock.lang.Unroll

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

    @Unroll
    def "Two arguments #expression #pointMessage"() {
        given:
        //Setup
        when:
        def result = instance.calculate(expression)
        then:
        printIfTrue(result == expectedResult,pointMessage)
        where :
        expression      | expectedResult        | pointMessage
        "1+1"           | Eval.me("1+1")        | ""
        "55+77"         | Eval.me("55+77")      | ""
        "77-55"         | Eval.me("77-55")      | ""
        "77-55"         | Eval.me("77-55")      | ""
        "99999-88888"   | Eval.me("99999-88888")| "[point-1]"
    }

    @Unroll
    def "Many arguments #expression #pointMessage"() {
        given:
        //Setup
        when:
        def result = instance.calculate(expression)
        then:
        printIfTrue(result == expectedResult,pointMessage)
        where :
        expression              | expectedResult                    | pointMessage
        "1+1-2"                 | Eval.me("1+1-2")                  | ""
        "55+77-88+99"           | Eval.me("55+77-88+99")            | ""
        "77-55+66-99+999-666"   | Eval.me("77-55+66-99+999-666")    | ""
        "99999-88888+7777-999"  | Eval.me("99999-88888+7777-999")   | "[point-1]"
    }

    @Unroll
    def "Extra operations #expression #pointMessage"() {
        given:
        //Setup
        when:
        double result = instance.calculate(expression)
        then:
        printIfTrue((result as double).round(2) == (expectedResult as double).round(2),pointMessage)
        where :
        expression              | expectedResult                    | pointMessage
        "1+1*2"                 | Eval.me("1+1*2")                  | ""
        "55+77*88+99/5"         | Eval.me("55+77*88+99/5")          | ""
        "77-55*66-99+999/666"   | Eval.me("77-55*66-99+999/666")    | ""
        "99999-88888/7777*999"  | Eval.me("99999-88888/7777*999")   | "[point-1]"
    }

    @Unroll
    def "Brackets operations #expression #pointMessage"() {
        given:
        //Setup
        when:
        double result = instance.calculate(expression)
        then:
        printIfTrue((result as double).round(2) == (expectedResult as double).round(2),pointMessage)
        where :
        expression                  | expectedResult                        | pointMessage
        "(1+1)*2"                   | Eval.me("(1+1)*2")                    | ""
        "(55+77)*88+99/5"           | Eval.me("(55+77)*88+99/5")            | ""
        "(77-55)*((66-99)+999)/666" | Eval.me("(77-55)*((66-99)+999)/666")  | ""
        "(99999-88888)/7777*999"    | Eval.me("(99999-88888)/7777*999")     | "[point-1]"
    }


    def printIfTrue (Boolean flag, String s){
        if(false){
            println s
        }
        flag
    }

}
