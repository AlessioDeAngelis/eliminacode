package it.alessio.tastierino.news;

import it.alessio.tabellone.news.Feed;
import it.alessio.tabellone.news.FeedMessage;
import it.alessio.tabellone.news.RssFeedParser;

public class RSSTest {
	public static void main(String[] args) {
		RssFeedParser parser = new RssFeedParser("http://ansa.feedsportal.com/c/34225/f/621689/index.rss");
		Feed feed = parser.readFeed();
		// System.out.println(feed);
		for (FeedMessage message : feed.getMessages()) {
			System.out.println(message.getTitle());
			System.out.println(message.getDescription());
			System.out.println();

		}

	}

}