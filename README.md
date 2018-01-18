# OnboardingLabs

# Objective:  

  - To post a tweet using Twitter
  - Retrieve latest home timeline from Twitter


## How To Use (for Mac):
  - create a directory in Finder or in Terminal where the program will be located
  - open Terminal
  - in Terminal, enter the following:
    ```
    cd {location of directory you created above}

    git clone https://github.com/briansheen/OnboardingLabs.git

    cd OnboardingLabs/

    git checkout Lab10_DependencyInjection
    ```

### Create your YML File

  - create and open {your-file-name}.yml
  - fill in oAuthConsumerKey, oAuthConsumerSecret, oAuthAccessToken, oAuthAccessTokenSecret with your own Twitter app credentials under the twitterKey parent class
  - see twitter-app-example.yml for example


### See Test Coverage

    mvn test

  - After running mvn test, open the Jacoco generated html file located at: {location of your project directory}/OnboardingLabs/target/site/jacoco/index.html

### Run Using Maven

    mvn package
    java -jar target/OnboardingLabs-1.0-SNAPSHOT.jar server {your-file-name}.yml


#### GetTimeline

  - enter http://localhost:8080/api/1.0/twitter/timeline into your URL bar in a web browser

or

    curl localhost:8080/api/1.0/twitter/timeline


#### GetFilteredTimeline

  - enter http://localhost:8080/api/1.0/twitter/filter?filter={your filter} into your URL bar in a web browser

or

    curl localhost:8080/api/1.0/twitter/filter?filter={your filter}


#### Tweet

    curl -X POST -d 'message={your tweet message}' localhost:8080/api/1.0/twitter/tweet
