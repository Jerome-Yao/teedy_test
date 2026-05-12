pipeline {
    agent any
    
    // ❌ 删除了 tools { dockerTool 'docker' }，绝不能让 Jenkins 乱下载 docker 客户端！
    
    environment {
        // 绑定 Docker Hub 凭据（这会自动生成 DOCKER_CREDS_USR 和 DOCKER_CREDS_PSW）
        DOCKER_CREDS = credentials('docker-cred') 
        DOCKER_IMAGE = 'yaoshengqi/ysqteedy'
        DOCKER_TAG = "${env.BUILD_NUMBER}"
        
        // ✅ 关键修复 1：把你的 OrbStack 真实路径加进 PATH（注意替换成你的实际路径，基于之前的报错日志是 ysq）
        PATH = "/Users/ysq/.orbstack/bin:/opt/homebrew/bin:/usr/local/bin:${env.PATH}"
        
    }

    stages {
        stage('Checkout & Build') {
            steps {
                // 拉取代码
                checkout scmGit(
                    branches: [[name: 'b-12411126']],
                    extensions:[],
                    userRemoteConfigs: [[url: 'https://github.com/Jerome-Yao/teedy_test.git']]
                )
                // 编译打包
                sh 'mvn -B -DskipTests clean package'
            }
        }

        stage('Building image') {
            steps {
                // ✅ 关键修复 3：使用纯 sh，加上 --progress=plain 彻底杜绝 Segmentation fault
                sh 'docker build --progress=plain -t ${DOCKER_IMAGE}:${DOCKER_TAG} .'
                // 顺便打个 latest 标签
                sh 'docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest'
            }
        }

        stage('Upload image') {
            steps {
                // ✅ 关键修复 4：用纯 sh 登录和推送，彻底避开 docker.withRegistry 插件找不到命令的 Bug
                sh 'echo $DOCKER_CREDS_PSW | docker login -u $DOCKER_CREDS_USR --password-stdin'
                sh 'docker push ${DOCKER_IMAGE}:${DOCKER_TAG}'
                sh 'docker push ${DOCKER_IMAGE}:latest'
            }
        }

        stage('Run containers') {
            steps {
                // 停止并删除旧容器
                sh 'docker stop teedy-container-8081 || true'
                sh 'docker rm teedy-container-8081 || true'
                
                // ✅ 使用原生命令启动容器
                sh 'docker run --name teedy-container-8081 -d -p 8081:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}'
                
                // 查看运行状态
                sh 'docker ps --filter "name=teedy-container"'
            }
        }
    }
}