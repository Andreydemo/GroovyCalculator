import codeanalis.Analiser
import groovy.io.FileType
@Grapes(
        @Grab(group = 'org.apache.commons', module = 'commons-io', version = '1.3.2')
)
import org.apache.commons.io.FileUtils

import java.nio.file.Files
import java.nio.file.Paths

import static groovy.io.FileType.FILES

/**
 * Created by andrii_korkoshko on 19.10.16.
 */
def readLine = { ->
    System.in.newReader().readLine()
}
def commands = [] as HashMap
def baseDir = "../../../projects";
def cdpSufix = "/M1/Hw2/src"
def cdpSufix2 = "/M1/Hw2/GroovyCalculator/src"
def testFolserSufix = "/test"

def myTestDir = "../../test"

def checkPath = { prefix ->
    if (new File("${prefix}/java/com/epam/cdp/caclulator").exists()) {
        return "/java/com/epam/cdp/caclulator"
    } else {
        return "/java/com/epam/cdp/calculator"
    }
}
commands.checkOne = { ->

    println "Input the name of the mentee"

    def name = readLine()
    println "Checking ${name}"

    println "Use default admin password?(y/n)"
    def yesNo = readLine()
    def adminPassword;
    if ("n".equalsIgnoreCase(yesNo)) {
        println "Please input admin password"
        adminPassword = System.console().readPassword()
    } else {
        new File("../../resources/password.default").withReader { adminPassword = it.readLine() }
    }


    def repoURI = "https://Andrii_Korkoshko:${adminPassword}@gitbud.epam.com/${name}/cdp_jamp_q3q4_2016.git"
    def gitClone = {
        new File("${baseDir}/${name}").mkdirs()
        def proc = "git clone ${it} ${baseDir}/${name}".execute(null, new File("."))
        proc.in.eachLine { line ->
            println line
        }

        proc.err.eachLine { line ->
            println line
        }
    }

    gitClone(repoURI)

    def chooseCdpSufix = { ->
        if (new File("${baseDir}/${name}/${cdpSufix}").exists())
            cdpSufix
        else
            cdpSufix2
    }

    def cdpSufixFixed = chooseCdpSufix()
    println "choosen tree $cdpSufixFixed"

    def mark = new Analiser().analise("${baseDir}/${name}/${cdpSufixFixed}")
    def testPath = "${baseDir}/${name}/${cdpSufixFixed}/${testFolserSufix}"
    def linesNumber = 0
    new File(testPath).eachFileRecurse(FILES) {
        if (it.name.endsWith('.groovy')) {
            Files.lines(Paths.get(it.absolutePath)).forEach({ line ->
                linesNumber++
            })
        }
    }
    if (linesNumber > 90) {
        mark += 2
    }

    def testPackage = checkPath(testPath)
    testPath = testPath + testPackage
    myTestDir = myTestDir + testPackage
    new File(testPath).deleteDir()
    println "Coppy ${Paths.get(myTestDir)} to ${Paths.get(testPath)}"
    FileUtils.copyDirectory(new File(myTestDir), new File(testPath))


    def mvnProc = "mvn -f ${baseDir}/${name}/${cdpSufixFixed}/.. clean install".execute(null, new File("."))
    def succes = false
    mvnProc.in.eachLine { line ->
        if (line.contains("BUILD SUCCESS")) {
            succes = true
        }
        println line
    }

    mvnProc.err.eachLine { line ->
        println line
    }
    if (succes) {
        mark += 7
    }
    println "SUMMARY MARK!! = ${mark}"
}

commands.check = { ->
    def resultFile = new File("../../resources/results.txt")
    resultFile.delete()
    resultFile.createNewFile()
    Files.lines(Paths.get(new File("../../resources/mentees.txt").absolutePath)).forEach({ mentee ->

        try {
            def name = mentee
            println "Checking ${name}"

            def adminPassword = null
            new File("../../resources/password.default").withReader {
                adminPassword = it.readLine()
            }

            def repoURI = "https://Andrii_Korkoshko:${adminPassword}@gitbud.epam.com/${name}/cdp_jamp_q3q4_2016.git"
            def gitClone = {
                new File("${baseDir}/${name}").mkdirs()
                def proc = "git clone ${it} ${baseDir}/${name}".execute(null, new File("."))
                proc.in.eachLine { line ->
                    println line
                }

                proc.err.eachLine { line ->
                    println line
                }
            }

            gitClone(repoURI)

            def chooseCdpSufix = { ->
                if (new File("${baseDir}/${name}/${cdpSufix}").exists())
                    cdpSufix
                else
                    cdpSufix2
            }

            def cdpSufixFixed = chooseCdpSufix()
            println "choosen tree $cdpSufixFixed"

            def mark = new Analiser().analise("${baseDir}/${name}/${cdpSufixFixed}")
            def testPath = "${baseDir}/${name}/${cdpSufixFixed}/${testFolserSufix}"
            def linesNumber = 0
            new File(testPath).eachFileRecurse(FILES) {
                if (it.name.endsWith('.groovy')) {
                    Files.lines(Paths.get(it.absolutePath)).forEach({ line ->
                        linesNumber++
                    })
                }
            }
            if (linesNumber > 70) {
                mark += 2
            }

            def testPackage = checkPath(testPath)
            def fixedTestPath = testPath + testPackage
            def fixedMyTestDir = myTestDir + testPackage
            new File(testPath).deleteDir()

            println "Coppy ${Paths.get(fixedMyTestDir)} to ${Paths.get(fixedTestPath)}"
            println "Coppy ${new File(fixedMyTestDir).getAbsolutePath()} to ${new File(fixedTestPath).getAbsolutePath()}"
            try {
                FileUtils.copyDirectory(new File(fixedMyTestDir), new File(fixedTestPath))
                println "coppy succes"
            } catch (Throwable e){
                e.printStackTrace()
            }

            def mvnProc = "mvn -f ${baseDir}/${name}/${cdpSufixFixed}/.. clean install".execute(null, new File("."))
            def succes = false
            mvnProc.in.eachLine { line ->
                if (line.contains("BUILD SUCCESS")) {
                    succes = true
                }
                println line
            }

            mvnProc.err.eachLine { line ->
                println line
            }
            if (succes) {
                mark += 7
            }
            println "SUMMARY MARK!! = ${mark}"

            resultFile << "$name - $mark\n"
        } catch (Throwable e) {
            resultFile << "$mentee - ${e.toString()}\n"
        }
    })
}

commands.clear = { ->
    new File(baseDir).deleteDir()
}

commands.exit = { ->
    System.exit(0)
}
commands.list = { ->
    def menteeFile = "../../resources/mentees.txt"
    new File(menteeFile).eachLine {
        println it
    }
}

commands.help = { ->
    commands.each { k, v ->
        println k
    }
}


while (true) {

    println "input commands"
    def command = readLine()
    println "----start procecssing ${command}-----"
    commands[command]()

}