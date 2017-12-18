# OnboardingLabs

# Objective:  

  - To post a tweet using Twitter account @bsman
  - Retrieve latest home timeline from Twitter account @bsman


## How To Use (for Mac):
  - create a directory in Finder or in Terminal where the program will be located
  - open Terminal
  - in Terminal, enter the following:
    ```
    cd {location of directory you created above}

    git clone https://github.com/briansheen/OnboardingLabs.git

    cd OnboardingLabs/

    git checkout Lab4_ConfigFiles
    ```

### Create your YML File
    create and open {your-file-name}.yml

  - fill in oAuthConsumerKey, oAuthConsumerSecret, oAuthAccessToken, oAuthAccessTokenSecret with your own Twitter app credentials under the twitterKey parent class
  - see twitter-app-example.yml for example


### Run Using Maven

    mvn package
    java -jar target/OnboardingLabs-1.0-SNAPSHOT.jar server {your-file-name}.yml



#### GetTimeline

  - enter http://localhost:8080/api/1.0/twitter/timeline into your URL bar in a web browser

or

    curl localhost:8080/api/1.0/twitter/timeline

#### Tweet

    curl -X POST -d 'message={your tweet message}' localhost:8080/api/1.0/twitter/tweet
