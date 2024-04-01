def api = '38cb501e-da66-45ba-8c4b-11880cad04d2'
def org = 'e6122c21-84b0-4172-820e-07a47a1a79a6'


pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                script {
                    def mvn = tool 'maven';
                    sh "${mvn}/bin/mvn clean package --no-transfer-progress"
                }
            }
        }
        stage('snyk test') {
            steps {
                script {
                    sh "snyk auth ${api}"
                    sh "snyk config set org=${org}"
                    sh "chmod +x mvnw"
                    def snykTestOutput = sh(script: "snyk test --json-file-output=code-test.json --fail-on=all", returnStdout: true, returnStatus: true)
                    if (snykTestOutput != 0) {

                                    error 'Snyk found vulnerabilities in the code. Pipeline will be stopped.'
                                } else {
                                    echo 'Snyk did not find any vulnerabilities. Proceeding with the pipeline.'
                                }
                    //sh "snyk code test --report --project-name=\"CarShopSber\" --json-file-output=results-code.json --fail-on=all"
                    //sh "snyk monitor --org=${org}"
                }
            }
        }
        stage('Start Docker Compose') {
             steps {
                sh 'docker-compose up -d'
             }
        }
    }
}