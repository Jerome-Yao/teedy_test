pipeline {
    agent any
    stages {
        stage('Clean') {
            steps {
                sh '/opt/homebrew/bin/mvn clean'
            }
        }
        stage('Compile') {
            steps {
                sh '/opt/homebrew/bin/mvn compile'
            }
        }
        stage('Test') {
            steps {
                sh '/opt/homebrew/bin/mvn test -Dmaven.test.failure.ignore=true'
            }
        }
        stage('PMD') {
            steps {
                sh '/opt/homebrew/bin/mvn pmd:pmd'
            }
        }
        stage('JaCoCo') {
            steps {
                sh '/opt/homebrew/bin/mvn jacoco:report'
            }
        }
        stage('Javadoc') {
            steps {
                sh '/opt/homebrew/bin/mvn javadoc:javadoc'
            }
        }
        stage('Site') {
            steps {
                sh '/opt/homebrew/bin/mvn site'
            }
        }
        stage('Package') {
            steps {
                sh '/opt/homebrew/bin/mvn package -DskipTests'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: '**/target/site/**/*.*', fingerprint: true
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
            junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true, skipPublishingChecks: true
        }
    }
}
