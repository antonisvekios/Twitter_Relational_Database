package twitter;

import java.util.List;

/**
 * This program exercises the TwitterDatabaseAPI (MySQL implementation). Notice that nothing other
 * than the instantiation of the API shows us that the underlying database is Relational, or MySQL.
 */
public class Twitter {

  private static final TwitterDatabaseAPI api = new TwitterDatabaseMysql();

  public static void main(String[] args) throws Exception {

    // Authenticate access to the server.
    String url = "jdbc:mysql://localhost:3306/twitterrdb";
    String user = "root";//System.getenv("TWITTER_USER");
    String password = "12345";//System.getenv("TWITTER_PASSWORD");

    api.authenticate(url, user, password);

    /*api.postTweet(new Tweet(11,"test11"));
    api.postTweet(new Tweet(1,"test11"));
    api.postTweet(new Tweet(11,"test1"));
    api.postTweet(new Tweet(1,"test1"));
    api.postTweet(new Tweet(11,"test11"));*/
    List<Tweet> tweets = api.getTweets(11);
    for (Tweet t: tweets) {
      System.out.println(t.toString());
    }

    List<Integer> followees = api.getFollowees(6630);
    System.out.println(followees);
    List<Integer> followers = api.getFollowers(1);
    System.out.println(followers);

    List<Tweet> tweetsTimeline = api.getTimeline(6630);
    for (Tweet t: tweetsTimeline) {
      System.out.println(t.toString());
    }
    api.closeConnection();
  }
}
