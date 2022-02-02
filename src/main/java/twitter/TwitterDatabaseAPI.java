package twitter;

import java.util.List;

public interface TwitterDatabaseAPI {

  /**
   * Post the given tweet
   * @param t the tweet to be posted
   */
  public void postTweet(Tweet t);

  /**
   * Get the timeline of the given user (10 most recent tweets posted by user's followees)
   * @param user_id the user ID of the user
   * @return a list of the 10 most recent tweets posted by user's followees
   */
  public List<Tweet> getTimeline(int user_id);

  /**
   * Returns the followers of the given user.
   * @param user_id the ID of the user
   * @return the list of IDs of the followers
   */
  public List<Integer> getFollowers(int user_id);

  /**
   * Returns the users that the given user is following.
   * @param user_id the ID of the user
   * @return the list of IDs of the followees
   */
  public List<Integer> getFollowees(int user_id);

  /**
   * Returns the tweets posted by the given user.
   * @param user_id the ID of the user
   * @return the list of tweets
   */
  public List<Tweet> getTweets(int user_id);

  /**
   * Set connection settings
   * @param url
   * @param user
   * @param password
   */
  public void authenticate(String url, String user, String password);

  /**
   * Close the connection when application finishes
   */
  public void closeConnection();

}
