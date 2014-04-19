package it.alessio.tabellone.news;

public class FeedController {

	private int feedCounter;
	private Feed feed;
	private String rssUrl;

	public FeedController(String rssUrl) {
		this.feedCounter = 0;
		this.rssUrl = rssUrl;
	}

	public void initializeFeed() {
		RssFeedParser parser = new RssFeedParser(rssUrl);
		this.feed = parser.readFeed();
	}

	public FeedMessage giveNextMessage() {
		FeedMessage message = null;
		int size = this.feed.getMessages().size();
		if (feedCounter < size) {
			message = feed.getMessages().get(feedCounter);
			this.feedCounter = ((this.feedCounter) + 1) % size;
		}
		return message;
	}

	public String getRssUrl() {
		return rssUrl;
	}

	public void setRssUrl(String rssUrl) {
		this.rssUrl = rssUrl;
	}

}
