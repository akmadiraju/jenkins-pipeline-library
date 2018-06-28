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
        gradleBuild(tasks: 'assemble')
      }
    }

    stage('Unit Test') {
      steps {
        gradleBuild(tasks: 'check -Dhttp.socketTimeout=60000 -Dhttp.connectionTimeout=60000')
      }
    }
  }

  post {
    always {
      junit '**/build/test-results/test/TEST*.xml' // displays test results in jenkins console
    }
  }
}