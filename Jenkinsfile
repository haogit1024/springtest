#!/usr/bin/env groovy
pipeline{
    agent any
    // 可以设置环境变量
    environment {

    }
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
                sshPublisher(publishers: [sshPublisherDesc(configName: '136', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: 'D:\\file', remoteDirectorySDF: false, removePrefix: '/target', sourceFiles: '/target/*.war')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }
    }
}