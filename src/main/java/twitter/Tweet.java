package twitter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tweet implements Comparable<Tweet> {

  private int tweet_id;
  private int user_id;
  private Date tweet_ts;
  private String tweet_text;

  public Tweet(int tweet_id, int user_id, Date tweet_ts, String tweet_text) {
    this.tweet_id = tweet_id;
    this.user_id = user_id;
    this.tweet_ts = tweet_ts;
    this.tweet_text = tweet_text;
  }

  public Tweet(int user_id, String tweet_text) {
    this.tweet_id = -1;
    this.user_id = user_id;
    this.tweet_text = tweet_text;
    this.tweet_ts = null;
  }

  public int getTweetID() {
    return this.tweet_id;
  }

  public void setTweetID(int tweet_id) {
    this.tweet_id = tweet_id;
  }

  public int getUserID() {
    return this.user_id;
  }

  public void setUserID(int user_id) {
    this.user_id = user_id;
  }

  public Date getTweetTS() {
    return this.tweet_ts;
  }

  public void setTweetTS(Date tweet_ts) {
    this.tweet_ts = tweet_ts;
  }

  public String getTweetText() {
    return this.tweet_text;
  }

  public void setTweetText(String tweet_text) {
    this.tweet_text = tweet_text;
  }

  @Override
  public String toString() {
    return "Tweet{" +
        "tweet_id=" + tweet_id +
        ", user_id=" + user_id +
        ", tweet_ts='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(tweet_ts) + '\'' +
        ", tweet_text='" + tweet_text + '\'' +
        '}';
  }

  @Override
  public int compareTo(Tweet t) {
    if (this.getTweetTS() == null || t.getTweetTS() == null) {
      return 0;
    }
    return this.getTweetTS().compareTo(t.getTweetTS());
  }

}
