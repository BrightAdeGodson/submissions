/*
 * Generate simple random sentences using recursive.
 * Output: 
 * a woman who is tiny hates the bald. unicorn.
 * some bald. unicorn believes that Fred finds every pretty. fish.
 * some woman runs.
 * Fred is pretty.
 * some fish who sees every man is tiny.
 * Fred looks for a man but the big. unicorn jumps.
 * Miss America hates Miss America or the woman who sleeps jumps because every bald. man is pretty.
 * Miss America hates Jane.
 * some big. man believes that a fish who is tiny is pretty.
 * a elephant runs.
 * every bald. elephant who is tiny is pretty but Fred is bald.
 *  (Ctrl+C)
 */

public class SimpleRandomSentences {
	static final String[] conjunction = { "and", "or", "but", "because"};
	static final String[] properNoun = { "Fred", "Jane", "Richard Nixon","Miss America"};
	static final String[] commonNoun = { "man", "woman", "fish", "elephant", "unicorn"};   
	static final String[] determiner = { "a", "the", "every", "some"};
	static final String[] adjective = { "big", "tiny", "pretty", "bald"};
	static final String[] intransitiveVerb = { "runs", "jumps", "talks", "sleeps"};
	static final String[] transitiveVerb = { "loves", "hates", "sees", "knows", "looks for", "finds"};

	public static void main(String[] args) {
		while (true) {
			randomSentence();
			System.out.println(".\n\n");
			try {
				Thread.sleep(3000);
			}
			catch (InterruptedException e) {
			}
		}
	}

	static void randomSentence() {
		randomNounPhrase();
		randomVerbPhrase();
		if (Math.random() > 0.75) {
			// Recursively making compound sentences using conjunctions
			System.out.print(" " + randomItem(conjunction));
			randomSentence();
		}
	}

	static void randomNounPhrase() {
		if (Math.random() > 0.75) {
			System.out.print(" " + randomItem(properNoun));
		} else {
			System.out.print(" " + randomItem(determiner));
			if (Math.random() > 0.5) {
				System.out.print(" " + randomItem(adjective)+".");
			}
			System.out.print(" " + randomItem(commonNoun));
			if (Math.random() > 0.5) {
				System.out.print(" who" );
				randomVerbPhrase();
			}
		}
	}

	static void randomVerbPhrase() {
		if (Math.random() > 0.75) {
			System.out.print(" " + randomItem(intransitiveVerb));
		} else if (Math.random() > 0.50) {
			System.out.print(" " + randomItem(transitiveVerb));
			randomNounPhrase();
		} else if (Math.random() > 0.25) {
			System.out.print(" is " + randomItem(adjective));
		} else {
			System.out.print(" believes that");
			randomNounPhrase();
			randomVerbPhrase();
		}
	}

	static String randomItem(String[] listOfStrings) {
		return listOfStrings[(int)(Math.random()*listOfStrings.length)];
	}
}
