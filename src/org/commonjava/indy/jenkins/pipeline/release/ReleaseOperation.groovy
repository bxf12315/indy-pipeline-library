package org.commonjava.indy.jenkins.pipeline.release

import org.commonjava.indy.jenkins.pipeline.git.GitOperation
import org.commonjava.indy.jenkins.pipeline.maven.MavenOperation

def mavenOperation = new MavenOperation()
def gitOperation = new GitOperation()
boolean sanityBuild(){

}

boolean updateVersion(String version,String commitMassage,String remtoRepo,String targetBranch ){
    mavenOperation.updateApplicationVersionMaven(version)
    return gitOperation.gitCodePush(commitMassage,remtoRepo,targetBranch)
}