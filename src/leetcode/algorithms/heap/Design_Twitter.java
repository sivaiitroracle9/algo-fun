package leetcode.algorithms.heap;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Design_Twitter {

	Map<Integer, User> users;

	/** Initialize your data structure here. */
	public Design_Twitter() {
		users = new HashMap<>();
	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId) {
		if (!users.containsKey(userId)) {
			users.put(userId, new User(userId));
		}
		
		users.get(userId).addTweet(tweetId, new Feed(tweetId, System.currentTimeMillis()));
	}

	/**
	 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item
	 * in the news feed must be posted by users who the user followed or by the
	 * user herself. Tweets must be ordered from most recent to least recent.
	 */
	public List<Integer> getNewsFeed(int userId) {
		PriorityQueue<Feed> heap = new PriorityQueue<Feed>((o1, o2) -> {
			if (o2.getTimestamp() > o1.getTimestamp())
				return -1;
			else if (o2.getTimestamp() < o1.getTimestamp())
				return 1;
			else
				return 0;
		});

		Set<Feed> feeds = new HashSet<>();
		feeds.addAll(users.get(userId).getFeeds());
		for(int fid : users.get(userId).followers) {
			feeds.addAll(users.get(fid).getFeeds());
		}
		
		for(Feed feed : feeds) {
			if(heap.size()>10) 
				heap.poll();
			heap.offer(feed);
		}
		
		return null;
	}

	/**
	 * Follower follows a followee. If the operation is invalid, it should be a
	 * no-op.
	 */
	public void follow(int followerId, int followeeId) {
		users.get(followerId).addFollower(followeeId);
		users.get(followeeId).addFollower(followerId);
	}

	/**
	 * Follower unfollows a followee. If the operation is invalid, it should be
	 * a no-op.
	 */
	public void unfollow(int followerId, int followeeId) {
		users.get(followerId).removeFollower(followeeId);
		users.get(followeeId).removeFollower(followerId);
	}

	private class User {
		private Set<Integer> followers = new HashSet<>();
		private Map<Integer, Feed> tweets = new HashMap<>();
		private PriorityQueue<Feed> heap;
		private int userid;

		public User(int userid) {
			this.userid = userid;
			this.heap = new PriorityQueue<Feed>((o1, o2) -> {
				if (o2.getTimestamp() > o1.getTimestamp())
					return -1;
				else if (o2.getTimestamp() < o1.getTimestamp())
					return 1;
				else
					return 0;
			});
		}

		public void addFollower(int follower) {
			followers.add(follower);
		}

		public void removeFollower(int follower) {
			followers.remove(follower);
		}

		public void addTweet(int tweetId, Feed feed) {
			tweets.put(tweetId, feed);
			if(heap.size()>10) 
				heap.poll();
			heap.offer(feed);
		}

		public void removeTweet(int tweetId) {
			heap.remove(tweets.remove(tweetId));
			tweets.remove(tweetId);
		}
		
		public List<Feed> getFeeds(){
			return new ArrayList<Feed>(heap);
		}
	}

	private class Feed {
		private int tId;
		private long timestamp;

		Feed(int tId, long timestamp) {
			this.tId = tId;
			this.timestamp = timestamp;
		}

		public int gettId() {
			return tId;
		}

		public long getTimestamp() {
			return timestamp;
		}

	}
}
