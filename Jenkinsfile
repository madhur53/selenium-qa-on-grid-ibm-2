pipeline {
    agent any
    tools { maven 'Maven-3.9'; jdk 'JDK-17' }
    environment { BASE_URL = 'https://the-internet.herokuapp.com' }

    stages {
        stage('Checkout') {
            steps { checkout scm }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test -Dbase.url=${BASE_URL} -q'
            }
            post {
                always {
                    junit allowEmptyResults: true,
                          testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Generate HTML Report') {
            steps {
                // generates target/site/surefire-report.html from the XML results
                sh 'mvn surefire-report:report-only'
            }
        }

        stage('Publish Report') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/site',
                    reportFiles: 'surefire-report.html',
                    reportName: 'Test Report'
                ])
            }
        }
    }

    post {
        success { echo 'All tests green' }
        always  { cleanWs() }
    }
}