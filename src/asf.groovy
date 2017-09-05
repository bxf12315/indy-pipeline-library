import org.commonjava.indy.jenkins.pipeline.git.GitOperation

class asf{
    static void main(String[] args) {
        String a = "Hello World";

        println(a.matches("Hello"));
        println(a.matches("Hello(.*)"));

        def git = new GitOperation()
        git.echo();

        def abs = new asf()
        abs.p()
    }

    void p(){
        println("---2---2---2----2-------")
    }
}

