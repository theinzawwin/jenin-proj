pipeline {
    agent any
    tools {
        maven 'maven_3_8_3'
    }

    stages {
        stage('Maven Clean Install') {
            steps {
                script {
                    try {
                        checkout([$class: 'GitSCM',
                                  branches: [[name: '*/main']],
                                  userRemoteConfigs: [[url: 'https://github.com/theinzawwin/jenin-proj.git', credentialsId: 'theinzawwin-github']],
                                  extensions: []
                        ])
                    } catch (Exception e) {
                        echo "TZW Failed to checkout repository: ${e.message}"
                    }
                }
            }
        }

        stage('Maven Build and Install') {
            steps {
                sh 'mvn clean install package'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t trust-spring-jenkin .'
                }
            }
        }

        stage('Push Docker Image to Dockerhub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'theinzawwin-dockerhub', variable: 'DOCKER_PASSWORD')]) {
                        sh 'docker login -u theinzawwin -p ${DOCKER_PASSWORD}'
                    }
                    sh 'docker push theinzawwin/trust-spring-jenkin'
                }
            }
        }
    }
}
