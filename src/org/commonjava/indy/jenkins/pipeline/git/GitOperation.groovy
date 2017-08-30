package org.commonjava.indy.jenkins.pipeline.git


void gitClone(String repoUrl){
    sh ('git clone '+ repoUrl )
}

void createTag(String tagName,String comment) {
    sh ('git tag -a '+ $tagName + ' -m ' +$comment)
}

void pushTag(String tagName)
{
    sh ('git push origin '+$tagName)
}

String getBranch() {
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