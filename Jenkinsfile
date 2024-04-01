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
                    /*def mvn = tool 'maven';
                    sh "${mvn}/bin/mvn io.snyk:snyk-maven-plugin:2.2.0:code-test"
                    sh "snyk-to-html -i results-code.json -o results-code.html"
                    sh "${mvn}/bin/mvn io.snyk:snyk-maven-plugin:2.2.0:monitor"
                    sh "${mvn}/bin/mvn io.snyk:snyk-maven-plugin:2.2.0:test"
                    sh "${mvn}/bin/mvn io.snyk:snyk-maven-plugin:2.2.0:monitor"*/
                    sh "snyk auth ${api}"
                    sh "snyk config set org=${org}"
                    sh "snyk code test --json --fail-on=all"
                    sh "snyk test --json | snyk-to-html -o results-test.html"
                    sh "chmod +x mvnw"
                    sh "snyk monitor --org=${org}"
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