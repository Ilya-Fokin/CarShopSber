def projectName = 'CarShopSber'
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
                    sh 'snyk code test --all-projects'
                    sh 'snyk monitor --all-projects'
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