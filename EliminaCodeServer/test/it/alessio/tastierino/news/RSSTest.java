package it.alessio.tastierino.news;

import it.alessio.tabellone.news.Feed;
import it.alessio.tabellone.news.FeedMessage;
import it.alessio.tabellone.news.RssFeedParser;


public class RSSTest {
  public static void main(String[] args) {
    RssFeedParser parser = new RssFeedParser("http://data.kataweb.it/rss/news24repubblica/rss2.0.xml");
    Feed feed = parser.readFeed();
    System.out.println(feed);
    for (FeedMessage message : feed.getMessages()) {
    System.out.println(message.getTitle());
      System.out.println(message.getDescription());
      System.out.println();

    }

  }
} 