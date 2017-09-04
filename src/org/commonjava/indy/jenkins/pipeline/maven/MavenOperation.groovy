package org.commonjava.indy.jenkins.pipeline.maven

void buildWithParams(HashMap params){

}

void updateApplicationVersionMaven(String version) {
    sh "echo \'\ninfo.build.version=\'$version >> \$(find . -name application.properties | grep -E -v 'target|src/test') || true"
    sh "mvn -B versions:set -DnewVersion=$version"
}

void runSonarAnalysis(String sonarUrl) {
    //println 'Sonar analysis temporarily disabled';
    println 'Running Sonar analysis';
    sh "mvn -B -V -U -e org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar -Dsonar.host.url='$sonarUrl'"
}

void deployToNexus() {
    println 'Deploying to Nexus';
    sh "mvn -B -V -U -e clean source:jar javadoc:jar deploy -DskipTests"
}