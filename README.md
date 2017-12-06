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

    cd OnboardingLabs/src
    ```
### To Tweet:

    javac -cp {enter the directory path to OnboardingLabs folder}/lib/twitter4j-core-4.0.3.jar:. com/company/Tweet.java

    java -cp {enter the directory path to OnboardingLabs folder}/lib/twitter4j-core-4.0.3.jar:. com/company/Tweet '{your tweet}'

### To Get Timeline:

    javac -cp {enter the directory path to OnboardingLabs folder}/lib/twitter4j-core-4.0.3.jar:. com/company/GetTimeline.java

    java -cp {enter the directory path to OnboardingLabs folder}/lib/twitter4j-core-4.0.3.jar:. com/company/GetTimeline
    
