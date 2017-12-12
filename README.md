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

    git checkout Lab3_RESTful
    ```

### Run Using Maven

    mvn package
    java -jar target/OnboardingLabs-1.0-SNAPSHOT.jar server

#### GetTimeline

  - enter http://localhost:8080/api/1.0/twitter/timeline into your URL bar in a web browser

or

    curl localhost:8080/api/1.0/twitter/timeline

#### Tweet

    curl -X POST -d 'message={your tweet message}' localhost:8080/api/1.0/twitter/tweet
