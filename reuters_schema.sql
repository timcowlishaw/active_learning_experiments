CREATE TABLE articles (
  id SERIAL PRIMARY KEY,
  filename VARCHAR(32),
  text TEXT,
  test BOOLEAN
);

CREATE TABLE topics (
  id SERIAL PRIMARY KEY,
  name VARCHAR(32)
);

CREATE TABLE words (
  id SERIAL PRIMARY KEY,
  word VARCHAR(64),
  normalised VARCHAR(64),
  stemmed VARCHAR(64),
  alphanumeric BOOLEAN,
  stop BOOLEAN
);

CREATE TABLE article_word_instances (
  article_id INTEGER,
  word_id INTEGER,
  position INTEGER,
  CONSTRAINT articles_fk FOREIGN KEY (article_id) REFERENCES articles,
  CONSTRAINT words_fk FOREIGN KEY (word_id) REFERENCES words
);

CREATE TABLE article_topic_instances (
  article_id INTEGER,
  topic_id INTEGER,
  observed BOOLEAN,
  manual BOOLEAN,
  active BOOLEAN,
  ground BOOLEAN,
  CONSTRAINT articles_fk FOREIGN KEY (article_id) REFERENCES articles,
  CONSTRAINT topics_fk FOREIGN KEY (topic_id) REFERENCES topics
);

CREATE INDEX words_by_article ON article_word_instances (article_id, position);
CREATE INDEX articles_by_word ON article_word_instances (word_id);
CREATE INDEX articles_by_topic ON article_topic_instances (topic_id, observed, manual, active);
CREATE INDEX topics_by_article ON article_topic_instances (article_id, observed, manual, active);
CREATE INDEX articles_by_train_test ON articles (test);
CREATE INDEX normalised_words ON words (normalised);
CREATE INDEX stemmed_words ON words (stemmed);
CREATE INDEX alphanueric_words ON words (alphanumeric);
CREATE INDEX stop_words ON words (stop);
CREATE INDEX topics_by_name ON topics(name);

