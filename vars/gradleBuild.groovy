def call(Closure body){
    Map config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    call(config)
}

def call(Map config = [:]){

    gradleHmme = config.getOrDefault('gradleHme', '~/.sdkman/candidates/gradle/current/bin/gradle')
    withWrapper = config.getOrDefault('withWrapper', true)
    withJunit = config.getOrDefault('withJunit', false)
    gradleOpts = config.getOrDefault('goal','clean build')

    def gradleCmd = []
    if (withJunit){
        if (!(fileExists('./gradlew'))){
            throw "Wrapper file not found"
        }else{
            gradleCmd.add("./gradlew")
        }
    }else {
        gradleCmd.add(gradleHmme)
    }
    gradleCmd.add(gradleOpts)
    if (!withJunit){
        gradleCmd.add("-x test")
    }
    def greet = "Hello World"
    
    sh "echo ${greet}"

    sh "${gradleCmd.unique().join(" ")}"
}

return this;
