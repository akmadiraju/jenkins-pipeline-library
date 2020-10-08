def call(Closure body){
    Map config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    call(config)
}

def call(Map config = [:]){
    sh "./gradlew clean build"

    greeting=Hello
    echo "This is the $greeting" 

    withCredentials([usernamePassword(credentialsId: 'artifactory', passwordVariable: 'password', usernameVariable: 'username')]) {
        sh "gradle -Ppassword=${password} upload"
    }
    
}

return this;
