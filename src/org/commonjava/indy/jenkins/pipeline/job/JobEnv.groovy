package org.commonjava.indy.jenkins.pipeline.job

void setBuildName(String version, String branch) {
    currentBuild.displayName = version
    if (branch != 'master') {
        currentBuild.displayName = "# ${env.BUILD_NUMBER} - branch: ${branch} "
    }
}

void cleanWorkspace() {
    sh "echo 'Cleaning workspace'"
    deleteDir()
}

String getBuildVersion() {
    return env.BUILD_NUMBER;
}