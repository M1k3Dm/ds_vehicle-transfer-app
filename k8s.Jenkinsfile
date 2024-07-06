pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '30', artifactNumToKeepStr: '30'))
    }

    stages {

        /*stage('Checkout') {
            steps {
                git branch: 'master', url: 'git@github.com:M1k3Dm/ds_vehicle-transfer-app.git'
            }
        } */

        
        stage('run ansible pipeline') {
            steps {
                build job: 'ansible'
            }
        }
        stage('Deploy project with ansible') {
                    steps {
                        sh '''
                            export ANSIBLE_CONFIG=~/workspace/ansible/ansible.cfg
                            ansible-playbook ~/workspace/ansible/playbooks/k8s.yaml
                        '''
                    }
         }
    }

    /*post {
        always {
            mail  to: "argirispaok@gmail.com", body: "Project ${env.JOB_NAME} <br>, Build status ${currentBuild.currentResult} <br> Build Number: ${env.BUILD_NUMBER} <br> Build URL: ${env.BUILD_URL}", subject: "JENKINS: Project name -> ${env.JOB_NAME}, Build -> ${currentBuild.currentResult}"
        }
    }*/
}