def call(Closure body){
    Map config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    call(config)
}

def call(Map config = [:]){
    String shellScript = libraryResource("resources/test.sh")

    writeFile  file: "test.sh", text: shellScript, encoding: "UTF-8"

    sh """
        sh ./test.sh
     """
}