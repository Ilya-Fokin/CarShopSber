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
                    sh "snyk code test --json --org=${org} | snyk-to-html -o results-opensource.html"
                    sh "snyk test --json --org=${org} | snyk-to-html -o results-opensource.html"
                    sh "chmod +x mvnw"
                    sh "snyk monitor --org=${org} -d"
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