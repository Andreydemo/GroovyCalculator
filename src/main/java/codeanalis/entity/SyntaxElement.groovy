package codeanalis.entity

import groovy.transform.EqualsAndHashCode

/**
 * Created by andrii_korkoshko on 19.10.16.
 */
@EqualsAndHashCode
class SyntaxElement {

    int indexStart
    int indexEnd
    boolean closed
    String symbol
    String closedSymbol


}
