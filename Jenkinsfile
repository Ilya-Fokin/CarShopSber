pipeline {
    agent any

    environment {
    def api = '38cb501e-da66-45ba-8c4b-11880cad04d2'
    def org = 'e6122c21-84b0-4172-820e-07a47a1a79a6'

    def result_snyk_test = 0
    def result_snyk_test_json = 'snyk_test.json'
    def result_snyk_test_html = 'snyk_test.html'

    def result_snyk_code_test = 0
    def result_snyk_code_test_json = 'snyk_code_test.json'
    def result_snyk_code_test_html = 'snyk_code_test.html'

    def project_name = 'com.example:CarShopSber'
    }

    stages {
        stage('SCM') {
            steps {
                checkout scm
                echo '' + env.BRANCH_NAME
            }
        }
        stage('Build') {
            steps {
                buildProject()
            }
        }
        stage('Snyk configure') {
            steps {
                snykConfigure()
            }
        }
        stage('Snyk test') {
            steps {
                snykTest()
            }
        }
        /*stage('Snyk code test') {
            steps {
                snykCodeTest()
            }
        }*/
        stage ("Check results") {
            steps {
                checkResultsSnykTest()
            }
        }
        stage('Start Docker Compose') {
             steps {
                sh 'docker-compose up -d'
             }
        }
    }
}

def buildProject() {
    script {
        def mvn = tool 'maven'
        sh "${mvn}/bin/mvn clean package -DskipTests --no-transfer-progress"
    }
}

def snykConfigure() {
    script {
        sh "snyk auth ${api}"
        sh "snyk config set org=${org}"
        sh "chmod +x mvnw"
    }
}

def snykTest() {
    script {
        result_snyk_test = sh(script: "snyk test --json-file-output=${result_snyk_test_json}", returnStatus: true)
        sh "snyk monitor"
    }
}

/*def snykCodeTest() {
    script {
        result_snyk_code_test = sh(script: "snyk code test --json-file-output=${result_snyk_code_test_json}", returnStatus: true)
    }
}*/

def checkResultsSnykTest() {
    script {
        def recipients = 'fokin3349@mail.ru'
        //def recipients = emailextrecipients([ [$class: 'DevelopersRecipientProvider'],[$class: 'CulpritsRecipientProvider']])
        echo "Developer Email: ${recipients}"

        if (result_snyk_test != 0 || result_snyk_code_test != 0) {
            if (result_snyk_test != 0) {
                sendResultHtml(result_snyk_test_json, result_snyk_test_html, recipients)
            }
            if (result_snyk_code_test != 0) {
                sendResultHtml(result_snyk_code_test_json,result_snyk_code_test_html, recipients)
            }
            error 'Snyk test обнаружил уязвимости в проекте. Pipline остановлен.'
        }
        else {
            echo 'Уязвимости не найдены, выполнение pipline продолжается'
        }
    }
}

def sendResultHtml(result_json_file, test_html_file, recipient) {
    script {
        sh "snyk-to-html -i ${result_json_file} -o ${test_html_file}"
        emailext body: 'Snyk обнаружил уязвимости в Вашем коде. Пожалкйста, ознакомьтесь с отчетом',
                 subject: 'Найдены уязвимости в Вашем коммите',
                 to: "${recipient}",
                 mimeType: 'text/html',
                 attachmentsPattern: "${test_html_file}"
    }
}
