def jarName = 'CarShopSber-0.0.1-SNAPSHOT.jar'
def pathToProject = "/var/lib/jenkins/workspace/CarShopSber/target/${jarName}"
def snyk = '/usr/local/bin'
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
                    sh "snyk code test --org=e6122c21-84b0-4172-820e-07a47a1a79a6"
                    /*def mvn = tool 'maven';
                    sh "${mvn}/bin/mvn io.snyk:snyk-maven-plugin:2.2.0:code-test"
                    sh "${mvn}/bin/mvn io.snyk:snyk-maven-plugin:2.2.0:test"
                    sh "${mvn}/bin/mvn io.snyk:snyk-maven-plugin:2.2.0:monitor"*/
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