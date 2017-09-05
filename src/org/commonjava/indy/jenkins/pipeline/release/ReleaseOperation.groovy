package org.commonjava.indy.jenkins.pipeline.release

import org.commonjava.indy.jenkins.pipeline.docker.DockerOperation
import org.commonjava.indy.jenkins.pipeline.git.GitOperation
import org.commonjava.indy.jenkins.pipeline.maven.MavenOperation

def mavenOperation = new MavenOperation()
def gitOperation = new GitOperation()
def dockerOperation = new DockerOperation()

boolean sanityBuild(){

}

boolean updateVersion(String version,String commitMassage,String remtoRepo,String targetBranch ){
    mavenOperation.updateApplicationVersionMaven(version)
    return gitOperation.gitCodePush(commitMassage,remtoRepo,targetBranch)
}

boolean tagForRelease(String comment ,String tagName,String remoteRepo){
    gitOperation.createTag(tagName,comment)
    gitOperation.pushTag(tagName,remoteRepo)
}

boolean releaseToNexus(){
    mavenOperation.deployToNexus()
}

boolean dockerPush(){
    dockerOperation.buildImage()
    dockerOperation.commitImage()
    dockerOperation.pushImage()
}