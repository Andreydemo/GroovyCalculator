/**
 * Created by andrii_korkoshko on 19.10.16.
 */
def readLine = { ->
    System.in.newReader().readLine()
}
def commands = [] as HashMap
def baseDir = "/Users/andrii_korkoshko/development/projects/GroovyCalculator/projects";
commands.cloneOne = { ->

    println "Input the name of the mentee"

    def name = readLine()
    println "Checking ${name}"

    println "Please input admin password"
    def adminPassword = System.console().readPassword()

    def repoURI = "https://Andrii_Korkoshko:${adminPassword}@gitbud.epam.com/${name}/cdp_jamp_q3q4_2016.git"
    def gitClone = {
        new File("${baseDir}/${name}").mkdirs()
        def sout = new StringBuilder(), serr = new StringBuilder()
        def proc = "git clone ${it} ${baseDir}/${name}".execute(null, new File("."))
        proc.consumeProcessOutput(sout, serr)
        proc.waitForOrKill(10000)
        println "out> $sout err> $serr"

    }

    gitClone(repoURI)
}

commands.clear = {->
    new File(baseDir).deleteDir()
}

commands.exit = {->
    System.exit(0)
}


while (true) {

    println "input commands"
    def command = readLine()
    println "start procecssing ${command}"
    commands[command]()

}