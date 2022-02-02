import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import twitter.Tweet;
import twitter.TwitterDatabaseAPI;
import twitter.TwitterDatabaseMysql;


public class TimelineTest {

  private static final TwitterDatabaseAPI api = new TwitterDatabaseMysql();

  public static void main(String [] args) throws IOException {
    System.out.println("Starting timeline test:");
    timelineTest();
    System.out.println("Timeline test completed");
  }

  private static void timelineTest() throws IOException {
    // Authenticate access to the server.
    String url = "jdbc:mysql://localhost:3306/twitterrdb";
    String user = "root";
    String password = "12345";
    api.authenticate(url, user, password);

    // start timer
    long startTime = System.currentTimeMillis();

    // setting a timer for 10 minutes
    long timer = startTime + 10*60*1000;

    int numberOfTimeLines = 0;

    List<Tweet> timeline_results = new ArrayList();

    // loop through each line of the file
    while (System.currentTimeMillis() < timer){
      int random_user_id = (int) (Math.random() * 10000);
      timeline_results = api.getTimeline(random_user_id);
      numberOfTimeLines++;
    }
    // end timer
    long endTime = System.currentTimeMillis();

    // calculate duration
    double duration = (endTime-startTime)/1000.0;

    System.out.println("Total duration (in seconds): " + duration);
    System.out.println("Timelines retrieved per second: " + numberOfTimeLines/duration);
  }

}