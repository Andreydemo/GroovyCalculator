package codeanalis.finder

/**
 * Created by andrii_korkoshko on 19.10.16.
 */
class OperatorOwerloadingFInder implements Finder {

    def operators = ["plus", "minus", "multiply", "div", "mod", "power", "or", "and", "xor", "asType", "call"]
    public static final String call = "("

    @Override
    def find(String line) {
        for (String it : operators) {
            if (line.contains(it + call)) {
                println "OVERLOADING found opertor ${it} line : ${line}"
                break
            }
        }
    }


    def OperatorOwerloadingFInder mod(OperatorOwerloadingFInder it) {
        return it;
    }
}
