#!groovy


try {

  stage 'Checkout Code'
  node {
    deleteDir()
    checkout scm
    stash 'sources'
  }

  stage 'Gradle Build'
  node {
    deleteDir()
    unstash 'sources'
    gradleBuild {}
    stash includes: '**/*.jar,**/*.war', name: 'binaries'
  }



} catch (Exception ex) {
  sendFailureNotifications {
    emailCulprits = true // emails the suspected commit culprit(s)
    // notifySlackChannel = '<channel_name>' // send notification to the team's slack channel
  }

  throw ex // re-throw so that the exception bubbles up to the console
}
