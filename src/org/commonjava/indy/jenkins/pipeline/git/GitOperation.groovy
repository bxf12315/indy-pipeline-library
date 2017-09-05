package org.commonjava.indy.jenkins.pipeline.git

void gitClone(String repoUrl){
    sh ('git clone '+ repoUrl )
}

void createTag(String tagName,String comment) {
    sh ("git tag -a  $tagName  -m $comment")
}

void pushTag(String tagName,String remoteRepo)
{
    sh ("git push  $remoteRepo  $tagName")
}

boolean gitCodePush(String commitMassage,String remtoRepo,String targetBranch){
    def result
    try {
        sh("git add . ")
        sh("git commit -m $commitMassage  ")
        sh("git push $remtoRepo $tagetBranch  ")
        result = true
    } catch (err){
        result = false
    }
    return result
}

String getBranchName() {
    String branchName;

    // When Gitlab triggers the build, we can read the source branch from gitlab.
    if (env.gitlabSourceBranch != null) {
        branchName = env.gitlabSourceBranch
        echo 'Gitlab source branch: ' + branchName
    } else {
        sh "git show-ref | grep `git rev-parse HEAD` | grep remotes | grep -v HEAD | sed -e 's/.*remotes.origin.//' > branch.txt"
        branchName = readFile('branch.txt').trim()


    }

    echo 'Building branch \'' + branchName + '\'.'
    return branchName;
}

