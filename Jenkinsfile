pipeline {
    agent any
    tools {
        maven 'M3'
        dockerTool 'docker'
    }
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-cred')
 // Docker Hub Repository's name
        DOCKER_IMAGE = 'yaoshengqi/ysqteedy' // your Docker Hub user name and
        DOCKER_TAG = "${env.BUILD_NUMBER}" // use build number as tag
        PATH = "/usr/local/bin:${env.PATH}"
    }

 stages {
        stage('Build') {
            steps {
                checkout scmGit(
                    branches: [[name: 'b-12411126']],
                    extensions: [],
                    userRemoteConfigs: [[url: 'https://github.com/Jerome-Yao/teedy_test.git']]
              )
            sh 'mvn -B -DskipTests clean package'
        }
        }

        stage('Building image') {
            steps {
                script {
                    docker.build("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}")
                }
            }
        }

 // Uploading Docker images into Docker Hub
        stage('Upload image') {
            steps {
                script {
            // sign in Docker Hub
                    docker.withRegistry('https://registry.hub.docker.com','docker-cred') {
            // push image
            docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push()

            // ：optional: label latest
            docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push('latest')
                    }
                }
            }
        }

        stage('Run containers') {
            steps {
                script {
                    sh 'docker stop teedy-container-8081 || true'
                    sh 'docker rm teedy-container-8081 || true'
                    // run Container
                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run(
                    '--name teedy-container-8081 -d -p 8081:8080'
                    )
                    // Optional: list all teedy-containers
                    sh 'docker ps --filter "name=teedy-container"'

                }
            }
        }
    }
}