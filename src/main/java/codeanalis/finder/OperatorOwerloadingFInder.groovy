package codeanalis.finder

/**
 * Created by andrii_korkoshko on 19.10.16.
 */
class OperatorOwerloadingFInder implements Finder {

    def operators = ["plus", "minus", "multiply", "div", "mod", "power", "or", "and", "xor", "asType", "call"]
    public static final String call = "("

    @Override
    def boolean find(String line) {
        def result = false
        for (String it : operators) {
            if (line.contains(it + call)) {
                println "OVERLOADING found opertor ${it} line : ${line}"
                result = true
                break
            }
        }
        result
    }

    @Override
    double getMark() {
        return 1
    }

    def OperatorOwerloadingFInder mod(OperatorOwerloadingFInder it) {
        return it;
    }
}
