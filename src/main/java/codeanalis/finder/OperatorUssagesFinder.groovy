package codeanalis.finder

/**
 * Created by andrii_korkoshko on 19.10.16.
 */
class OperatorUssagesFinder implements Finder {

    def operators = ["..", "<=>", ".@", "*.", "?."]

    @Override
    def find(String line) {

        for (String it : operators) {
            if(line.contains(it)){
                println "GROOVY OPERATOR found ${line}"
                break;
            }
        }
    }
}
