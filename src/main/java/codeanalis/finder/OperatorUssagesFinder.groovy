package codeanalis.finder

/**
 * Created by andrii_korkoshko on 19.10.16.
 */
class OperatorUssagesFinder implements Finder {

    def operators = ["..", "<=>", ".@", "*.", "?."]

    @Override
    def boolean find(String line) {
        def result = false
        for (String it : operators) {
            if(line.contains(it)){
                println "GROOVY OPERATOR found ${line}"
                result = true
                break;
            }
        }
        result
    }

    @Override
    double getMark() {
        return 0.5
    }
}
