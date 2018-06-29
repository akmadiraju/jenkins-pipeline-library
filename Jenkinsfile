#!groovy

p = projectProperties().useGitVersion()
utils.useArtifactory(category: 'internal')

pipeline {
  agent
  options {
    timestamps()
    ansiColor('xterm')
  }
  stages {
    stage('Build') {
      steps {
        gradleBuild
        {
          tasks= 'clean build'
        }
      }
    }

  }

  post {
    always {
      junit '**/build/test-results/test/TEST*.xml' // displays test results in jenkins console
    }
  }
}