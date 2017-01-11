#!/usr/bin/groovy

package org.mangler.jenkins;

def notifyBuild(String buildStatus = 'STARTED', channel = '@econnell') {
    // build status of null means successful
    buildStatus =  buildStatus ?: 'SUCCESSFUL'

    // Default values
    def colorName = 'RED'
    def colorCode = '#FF0000'
    def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
    def summary = "${subject} branch: ${env.GIT_BRANCH} (${env.BUILD_URL})"

    // Override default values based on build status
    if (buildStatus == 'STARTED') {
        color = 'YELLOW'
            colorCode = '#FFFF00'
    } else if (buildStatus == 'SUCCESSFUL') {
        color = 'GREEN'
            colorCode = '#00FF00'
    } else {
        color = 'RED'
            colorCode = '#FF0000'
    }

    slackSend (color: colorCode, message: summary, channel: channel)
}
