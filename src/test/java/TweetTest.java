import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import twitter.Tweet;
import twitter.TwitterDatabaseAPI;
import twitter.TwitterDatabaseMysql;

public class TweetTest {

  private static final TwitterDatabaseAPI api = new TwitterDatabaseMysql();

  public static void main(String [] args) throws IOException {
    System.out.println("Starting tweet test:");
    tweetTest();
    System.out.println("Tweet test completed");
  }

  private static void tweetTest() throws IOException {
    // Authenticate access to the server.
    String url = "jdbc:mysql://localhost:3306/twitterrdb";
    String user = "root";
    String password = "12345";
    api.authenticate(url, user, password);
    // initialize file reader
    BufferedReader reader = new BufferedReader(new FileReader("tweet.csv"));

    System.out.println("Posting tweets from tweets.csv");
    // start timer
    long startTime = System.currentTimeMillis();

    // setting a timer for 10 minutes
    long timer = startTime + 10*60*1000;

    int numberOfTweets = 0;

    String tweetRow = reader.readLine(); //skip the first row (heading)
    // loop through each line of the file
    while (((tweetRow = reader.readLine()) != null) && (System.currentTimeMillis() < timer)){
      String[] tweetData = tweetRow.split(",");
      Tweet t = new Tweet(Integer.parseInt(tweetData[0]),tweetData[1]);
      api.postTweet(t);
      numberOfTweets++;
    }
    // end timer
    long endTime = System.currentTimeMillis();
    reader.close();
    System.out.println("Done reading tweets.csv");

    // calculate duration
    double duration = (endTime-startTime)/1000.0;

    System.out.println("Total duration (in seconds): " + duration);
    System.out.println("Tweets posted per second: " + numberOfTweets/duration);
  }

}
