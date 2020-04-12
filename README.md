# slackapi

Procedure to build and run the Slackapi tests

1. Clone the slackapi repo from github branch-name :- slackapi
    $ git clone https://github.com/ratiArun/slackapi.git
    $ cd slackapi
    
2. Build
    $ mvn clean install
    
3. Run
  i . Navigate to com.slackApi.tests.ChannelsTests class.
  ii. Run as testng with the parameter -Dhost=https://slack.com
  Note :- "host" : "Base url"
  
4. Verify
   i. The test report is created in the folder /test-output/emailable-report.html
