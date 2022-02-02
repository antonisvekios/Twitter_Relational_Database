package twitter;

import database.DBUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TwitterDatabaseMysql implements TwitterDatabaseAPI {

  DBUtils dbu;

  /**
   * Post the given tweet
   *
   * @param t the tweet to be posted
   */
  @Override
  public void postTweet(Tweet t) {
    String sql = String
        .format("INSERT INTO tweet (user_id, tweet_ts, tweet_text) VALUES(%d,NOW(4),'%s')",
            t.getUserID(), t.getTweetText());
    dbu.insertOneRecord(sql);
  }

  /**
   * Get the timeline of the given user (10 most recent tweets posted by user's followees)
   *
   * @param user_id the user ID of the user
   * @return a list of the 10 most recent tweets posted by user's followees
   */
  @Override
  public List<Tweet> getTimeline(int user_id) {
    // get user followees
    List<Integer> followees = getFollowees(user_id);
    // initialize list of tweets
    List<Tweet> tweets = new ArrayList<Tweet>();
    for (int id : followees) {
      // get tweets of followees
      tweets.addAll(getTweets(id));
    }
    // sort tweets based on date and time (newer -> older)
    Collections.sort(tweets, Collections.reverseOrder());
    // return 10 first tweets
    return tweets.stream().limit(10).collect(Collectors.toList());
  }

  /**
   * Returns the followers of the given user.
   *
   * @param user_id the ID of the user
   * @return the list of IDs of the followers
   */
  @Override
  public List<Integer> getFollowers(int user_id) {
    List<Integer> followers = new ArrayList<Integer>();
    try {
      Connection con = dbu.getConnection();
      Statement stmt = con.createStatement();
      String sqlGet =
          String.format("SELECT user_id FROM follows WHERE follows_id = '%d'", user_id);
      ResultSet rs = stmt.executeQuery(sqlGet);
      while (rs.next()) {
        followers.add(rs.getInt("user_id"));
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
    return followers;
  }

  /**
   * Returns the users that the given user is following.
   *
   * @param user_id the ID of the user
   * @return the list of IDs of the followees
   */
  @Override
  public List<Integer> getFollowees(int user_id) {
    List<Integer> followees = new ArrayList<Integer>();
    try {
      Connection con = dbu.getConnection();
      Statement stmt = con.createStatement();
      String sqlGet =
          String.format("SELECT follows_id FROM follows WHERE user_id = '%d'", user_id);
      ResultSet rs = stmt.executeQuery(sqlGet);
      while (rs.next()) {
        followees.add(rs.getInt("follows_id"));
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
    return followees;
  }

  /**
   * Returns the tweets posted by the given user.
   *
   * @param user_id the ID of the user
   * @return the list of tweets
   */
  @Override
  public List<Tweet> getTweets(int user_id) {
    List<Tweet> tweets = new ArrayList<Tweet>();
    try {
      Connection con = dbu.getConnection();
      Statement stmt = con.createStatement();
      String sqlGet =
          String.format("SELECT * FROM tweet WHERE user_id = '%d'", user_id);
      ResultSet rs = stmt.executeQuery(sqlGet);
      while (rs.next()) {
        tweets.add(
            new Tweet(rs.getInt("tweet_id"), rs.getInt("user_id"), rs.getTimestamp("tweet_ts"),
                rs.getString("tweet_text")));
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
    return tweets;
  }

  /**
   * Set connection settings
   *
   * @param url
   * @param user
   * @param password
   */
  @Override
  public void authenticate(String url, String user, String password) {
    dbu = new DBUtils(url, user, password);
  }

  /**
   * Close the connection when application finishes
   */
  @Override
  public void closeConnection() {
    dbu.closeConnection();
  }
}
