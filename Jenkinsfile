#!/usr/bin/env groovy

int timeOut = 60 * 1000

def testFunction(String message) {
    for (int i = 1; i < 5; i++) {
        Thread.sleep(5000)
        println(i)
        println(message)
    }
    throw new RuntimeException("服务器没有响应")
}

pipeline{
    agent any

    //默认命令运行的pwd 为项目workspace
    stages {
        stage('Build') {
            steps{
                echo '='*50 + 'package' + '='*50
                bat "mvn clean package -Dmaven.test.skip=true"
            }
        }
        // stage可以添加或减少
        stage('Test') {
            steps{
                echo 'This is a test step!'
                bat 'mvn test'
            }
        }
        stage('Deploy') {
            steps{
                echo 'This is a deploy step'
                testFunction("fuck you jenkinsfile")
//                sshPublisher(publishers: [sshPublisherDesc(configName: '136', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: 'D:\\file', remoteDirectorySDF: false, removePrefix: '/target', sourceFiles: '/target/*.war')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }
    }
}