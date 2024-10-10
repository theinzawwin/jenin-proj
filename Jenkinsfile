pipeline {
    agent any
    tools{
        maven 'maven_3_8_3'
    }

    stages{
        stage('Maven Clean Install'){
            steps {
                script{
                    try {
                                                checkout scm: [
                                                    $class: 'GitSCM',
                                                    branches: [[name: 'main']],
                                                    userRemoteConfig: [
                                                        url: 'https://github.com/theinzawwin/jenin-proj.git',
                                                        credentialsId: 'theinzawwin-github'
                                                    ]
                                                ]
                                           } catch (Exception e) {
                                               echo "Failed to checkout repository: ${e.message}"
                                            }

                }

              }
        }
        stage('Maven Build and Install') {
                    steps {
                        sh 'mvn clean install package'
                    }
                }
        stage('Build Docker Image'){
            steps{
                script{
                    sh 'docker build -t trust-spring-jenkin .'
                }
            }
        }
        stage('Push Docker Image to Dockerhub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'theinzawwin-dockerhub', variable: 'DOCKER_PASSWORD')]) {
                sh 'docker login -u theinzawwin -p ${${credentials('theinzawwin-dockerhub').getPassword()}'
}
                   sh 'docker push theinzawwin/trust-spring-jenkin'
                }
            }
        }
    }
}