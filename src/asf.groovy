
    static void main(String[] args) {
        sh("git log >> outfile")
        println("1111111111")
        new File("outfile").eachLine {
            line -> println ("line : $line")
        }
    }

